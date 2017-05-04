# bowling-game
## 요구사항
- 한 게임은 10개의 프레임(Frame)으로 구성
- 한 프레임에서 10개의 핀(pin)을 쓰러뜨리기 위해 2번의 기회(role)를 가짐
- 두번의 시도에서 핀을 모두 쓰러뜨리지 못하면 해당 프레임의 점수는 쓰러 뜨린 핀의 합
- 두번의 시도에서 핀을 모두 쓰러뜨리면 스페어(Spare).
    - 점수는 10 + 다음 롤(role)에서 쓰러뜨린 핀 수.
- 첫번째 시도에서 핀을 모두 쓰러뜨리면 스트라이크(Strike).
    - 점수는 10 + 다음 두번의 롤(role)에서 쓰러뜨린 핀 수.
- 10번째 프레임에서 스페어 또는 스트라이크 처리를 하면 하나 또는 두개의 보너스 기회를 얻음.
    - 단, 단지 보너스 기회에서 쓰러 뜨린 핀 수만을 더함.

## 참고
- [백명석님-볼링게임](https://github.com/msbaek/bowling-game) 
- [Ron Jeffries-The Bowling Game](http://ronjeffries.com/xprog/articles/acsbowling/)
