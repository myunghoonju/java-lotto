# 로또
## 진행 방법
* 로또 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

---

# [2단계] 문자열 덧셈 계산기를 통한 TDD/리팩토링 실습

## 기능 요구사항

- 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환 (예: “” => 0, "1,2" => 3, "1,2,3" => 6, “1,2:3” => 6)
- 앞의 기본 구분자(쉼표, 콜론)외에 커스텀 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 “//”와 “\n” 사이에 위치하는 문자를 커스텀 구분자로 사용한다. 예를 들어 “//;\n1;2;3”과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.
- 문자열 계산기에 숫자 이외의 값 또는 음수를 전달하는 경우 RuntimeException 예외를 throw한다.

## 프로그래밍 요구사항

- indent(들여쓰기) depth를 2단계에서 1단계로 줄여라.
  - depth의 경우 if문을 사용하는 경우 1단계의 depth가 증가한다. if문 안에 while문을 사용한다면 depth가 2단계가 된다.
- 메소드의 크기가 최대 10라인을 넘지 않도록 구현한다.
  - method가 한 가지 일만 하도록 최대한 작게 만들어라.
- else를 사용하지 마라.

## 기능 목록 리스트

- [x] 빈 문자열 또는 null 값을 입력할 경우 0을 반환
- [x] 숫자 하나를 문자열로 입력할 경우 해당 숫자를 반환
- [x] 숫자 두개를 컴마(,) 구분자로 입력할 경우 두 숫자의 합을 반환(예: "1,2")
  - [x] int의 범위를 넘어가는 문자일 경우
- [x] 구분자를 컴마(,) 뿐만 아니라 콜론(:)을 사용할 수 있다.
  - [x] 컴마만 사용된 문자열
  - [x] 콜론만 사용된 문자열
  - [x] 혼합으로 사용된 문자열
- [x] “//”와 “\n” 문자 사이에 커스텀 구분자를 지정할 수 있다. 
  - [x] “//”와 “\n” 문자 사이의 문자열을 찾아야 한다.
  - [x] 찾은 문자열이 커스텀 구분자로 이어져야 한다.
  - [x] 커스텀 구분자는 문자 제한이 없다.
- [x] 음수 또는 문자를 전달할 경우 RuntimeException 예외가 발생해야 한다.
  - [x] 커스텀 구분자 외 문자가 음수 또는 문자일 경우  RuntimeException 예외가 발생
  - [x] 콤마(,), 콜론(:) 외 문자가 음수 또는 문자일 경우  RuntimeException 예외가 발생



# [3단계] 로또(자동)

**요구사항 분석**

- 입력받은 금액에 한해서 1000원 단위의 로또를 구매할 수 있다.
- 자동으로 로또 번호를 생성할 수 있다.(로또 자동 생성은 Collections.shiffle() 메소드를 사용한다.)
- 로또 숫자는 1부터 45까지이다.
- 당첨 통계는 3개 일치, 4개 일치, 5개일치, 6개 일치가 있다.
- 3개 숫자 일치의 당첨금은 5000원이다.
- 4개 숫자 일치의 당첨금은 50000원이다.
- 5개 숫자 일치는 당첨금은 1500000원(150만원)이다.
- 6개 숫자 일치의 당첨금은 2000000000원 이다.
- 당첨된 총액과 구입 금액의 수익률을 알 수 있다. 기준은 1이다. 그렇다면, (수익률 / 구입 금액) 일 것이다.

- UI로직은 InputView, ResultView와 같은 클래스로 추가해 분리한다.

  

**메모**

- 수익률은 % 로 표현하지 않나?
- 자동으로 구매할 수 있다는 것은 수동으로 구매해야 하는 요구사항도 고려할 수 있어야 하지 않을까?
- 각 로또 숫자와 지난 주 당첨 번호는 어떻게 비교하면 좋을까?

- 당첨번호를 찾기 위한 방법으로 LinkedHashMap 사용을 고려해볼 수 있지 않을까?

- 정말 스트림을 사용하지 않으면 객체지향적으로 좀 더 고려해볼수 있는가?

  

**간단한 아키텍쳐**

LotteryBuyer - 로또구매자

LotteryStore - 로또를 판매하는 가게

LotteryInfoCenter - 로또의 당첨을 확인해주는 곳


LotteryTicket - 로또

**기능 구현 리스트**

- [x] 로또 구매자(LottoBuyer)는 일정 금액으로 로또를 LottoShop에서 구매할 수 있다.
  - [x] 로또티켓은 6개의 숫자를 가진다
  - [x] 구매자는 일정 금액을 소지할 수 있다.
  - [x] 처음 로또는 0개 가지고 있다.
  - [x] 금액 만큼의 로또를 LotteryStore으로 부터 구매할 수 있다.
- [x] 로또를 판매하는 가게(LotteryStore)는 자동으로 숫자를 찍어내 금액만큼 만들어 로또 구매자(LotteryBuyer)에게 전달해준다.
  - [x] LotteryStore은 LotteryBuyer가 제시한 금액만큼의 로또를 전달해준다.
    - [x] LotteryTicket을 LotteryMachine을 통해 발행한다.
    - [x] LotteryStore 는 가격을 계산해서 갯수만큼 구매자에게 티켓을 제공한다.
  - [x] LotteryMachine을 통해 만들어진 Ticket은
    - [x] 6개의 숫자로 구성된다. (범위: 1- 45)
    - [x] 자동으로 랜덤하게 만들어진다. (범위: 1- 45)
    - [x] 마지막으로, 6개의 숫자는 정렬된 형태를 가진다.
  
- [x] 로또당첨정보센터(LotteryInfoCenter)은 지난 주 당첨번호로 로또구매자(LotteryBuyer)의 로또 당첨을 확인해준다.
  - [x] System으로 부터 지난주당첨번호를 제공받는다
  - [x] 로또당첨 정보센터는 당첨 통계를 제공한다.(3개 일치, 4개 일치, 5개 일치, 6개 일치)
  - [x] 구매한 금액 대비 수익률을 제공한다.

- [ ] 추후 리팩토링을 고려해볼만 사항
  - [x] TicketList 에 대한 일급 컬렉션 고려하기
  - [x] MatchType에 대한 일급 컬렉션 고려하기
  - [ ] 복권 번호에 대한 일급 컬렉션 고려하기  
  - [x] View 개발
  - [x] Random Number 리스트로 변경하기
  - [ ] Integer는 사실 Number이지 않을까?
  

# [4단계] 로또(2등)
```text
[... 생략 ...]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6
보너스 볼을 입력해 주세요.
7

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
5개 일치, 보너스 볼 일치(30000000원) - 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
```

- [x] 보너스볼 변수 생성
- [x] 보너스볼 체크 로직 추가
- [x] 보너스볼 당첨시 전체 금액 확인
- [x] 보너스볼을 물어보는 메세지 출력 
- [x] 보너스볼이 포함된 메세지 출력

--- 그외 고려해볼만한 리팩토링 
- [x] matchCount 의 integer을 통해 matchType 개선
- [x] infoCenter 테스트 코드 리팩토링

# [5단계] 로또(수동)

// TODO
