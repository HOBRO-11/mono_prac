local key = KEYS[1]
local ttl = tonumber(ARGV[1])
local uuid = ARGV[2]

-- 키가 이미 존재하는지 확인
if redis.call("EXISTS", key) == 1 then
    -- 키가 존재하면 그 키의 남은 TTL을 반환
    local pttl = redis.call("TTL", key)
    return tostring(pttl)
else
    -- 키가 존재하지 않으면 uuid 값으로 새로운 string을 생성하고 ttl 설정
    redis.call("SET", key, uuid)
    redis.call("EXPIRE", key, ttl)
    -- 성공적으로 작업을 마쳤을 때 1 반환
    return tostring(1)
end
