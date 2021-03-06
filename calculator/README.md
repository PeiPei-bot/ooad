# 문자열 계산기 실습

## 요구사항

- 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환 (예: “” => 0, "1,2" => 3, "1,2,3" => 6, “1,2:3” => 6)
- 앞의 기본 구분자(쉼표, 콜론)외에 커스텀 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 “//”와 “\n” 사이에 위치하는 문자를 커스텀 구분자로 사용한다. 예를 들어 “//;\n1;2;3”과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.
- 문자열 계산기에 숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException 예외를 throw한다.

## 프로그래밍 요구사항
- indent(들여쓰기) depth를 2단계에서 1단계로 줄여라.
    - depth의 경우 if문을 사용하는 경우 1단계의 depth가 증가한다. if문 안에 while문을 사용한다면 depth가 2단계가 된다.
- else를 사용하지 마라.
- method가 한 가지 일만 하도록 최대한 작게 만들어라.

## 추가 요구사항
- 숫자 값을 위해 int과 같은 primitive type을 직접 사용하지 말고 자바 객체를 생성해 구현해 본다.
- 문자열을 split하는 규칙이 현재는 기본 구분자와 custom 구분자 두 가지가 있다. 앞으로 문자열을 split하는 규칙이 계속해서 추가될 것으로 예상한다. 규칙이 추가되더라도 영향을 최소화하면서 확장 가능하도록 구현한다.
- java 8에 추가된 람다를 활용해 위 요구사항을 구현해 본다.

# 토의 내용
## 리팩토링과 테스트
- 리팩토링은 설계다
- 설계에 정답은 없다
- 테스트 코드가 있으면 다양한 설계를 해볼 수 있다 = 맘 놓고 삽질할 수 있다
- 지나친 테스트를 작성하면 유지보수가 더 힘들어진다.
- 꼭 필요한 테스트만 작성하는 것도 중요한 능력이다.


