# auction-sniper

> 테스트 주도 개발로 배우는 객체 지향 설계와 실천 (로버트 C. 마틴 저) - 3부 동작하는 예제

## Open Fire Setting

```shell
$ docker run --name openfire -d \
        --publish 9090:9090 \
        --publish 5222:5222 \
        --publish 7777:7777 \
        sameersbn/openfire:3.10.3-19
```

Set domain name: localhost

1. Administration Console Login (localhost:9090)
2. Users/Groups > Create New User
    - sniper / sniper
    - auction-item-54321 / auction
    - auction-item-65432 / auction
3. Server > Server Settings > Resource Policy
    - Set Conflict Policy : Never Kick
