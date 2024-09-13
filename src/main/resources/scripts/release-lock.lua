local key = KEYS[1]
local uuid = ARGV[1]

-- 키가 존재하지 않는 경우 1 반환
if redis.call("EXISTS", key) == 0 then
    return tostring(2)
end

-- 키가 존재하는 경우, 그 값이 주어진 uuid와 같은지 확인
local current_uuid = redis.call("GET", key)
if current_uuid == uuid then
    -- uuid가 같으면 키를 삭제하고 1 반환
    redis.call("DEL", key)
    return tostring(1)
else
    -- uuid가 다르면 0 반환
    return tostring(0)
end
