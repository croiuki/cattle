set -e

check_debug()
{
    if [ -n "$CATTLE_SCRIPT_DEBUG" ] || echo "${@}" | grep -q -- --debug; then
        export CATTLE_SCRIPT_DEBUG=true
        export PS4='[${BASH_SOURCE##*/}:${LINENO}] '
        set -x
    fi
}

debug_on()
{
    CATTLE_SCRIPT_DEBUG=true check_debug
}

has_jq()
{
    if [ -x "$(which jq)" ]; then
        return 0
    fi

    return 1
}

docker_env()
{
    if [[ -e /.dockerenv && has_jq ]]; then
        eval $(cat /.dockerenv | jq -r '@sh "export \(.[])"')
    fi
}

info()
{
    echo "INFO:" "${@}"
}

error()
{
    echo "ERROR:" "${@}" 1>&2
}

call_curl()
{
    local curl="curl -s" 
    if [ -n "$CATTLE_AGENT_INSTANCE_AUTH" ]; then
        $curl -H "Authorization: $CATTLE_AGENT_INSTANCE_AUTH" "$@"
    elif [ -n "$CATTLE_ACCESS_KEY" ]; then
        $curl -u ${CATTLE_ACCESS_KEY}:${CATTLE_SECRET_KEY} "$@"
    else
        $curl "$@"
    fi
}

get()
{
    call_curl --retry 5 "$@"
}

post()
{
    call_curl -X POST "$@"
}

put()
{
    call_curl -X PUT "$@"
}

get_config()
{
    ${CATTLE_HOME}/config.sh "$@"
}

stage_files()
{
    if [ -d content-home ]; then
        tar cf - -C content-home . | tar xvf - -C $CATTLE_HOME
    fi

    if [ -d content ]; then
        tar cf - -C content . | tar xvf - -C /
    fi
}

check_debug
docker_env

export CATTLE_HOME=${CATTLE_HOME:-/var/lib/cattle}
export CATTLE_AGENT_LOG_FILE=${CATTLE_AGENT_LOG_FILE:-${CATTLE_HOME}/agent.log}
export CATTLE_CONFIG_URL=${CATTLE_CONFIG_URL:-$CATTLE_URL}
export CATTLE_STORAGE_URL=${CATTLE_STORAGE_URL:-$CATTLE_URL}
