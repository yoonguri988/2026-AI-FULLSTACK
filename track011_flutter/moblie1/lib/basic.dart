// 1. 실행법: 터미널에서 dart run lib/basic.dart 입력
// Flutter/Dart 기본 문법 연습용 종합 예제

void main() {
  // 1. 변수 선언 및 데이터 타입
  int number = 10;
  double pi = 3.14;
  String name = "Flutter";
  bool isAwesome = true;

  print("number: $number, pi: $pi, name: $name, isAwesome: $isAwesome");
  // 2. 리스트(List) - 순서가 있는 목록
  List<String> fruits = ["apple","banana","coconut"];
  fruits.add("orange");
  print("2. List: $fruits");

  // 3. 맵(Map) - Key-Value 쌍 구조
  Map<String, int> scores = {"math":90, "eng": 85};
  scores["science"] = 95;
  print("3. Map: $scores");

  // 4. 조건문
  if(number > 5){
    print("number는 5보다 크다");
  } else {
    print("number는 5보다 작거나 같다");
  }

  // 5. 반복문
  for(var fruit in fruits){
    print("과일 목록: $fruit");
  }

  // 6. 함수(Function)
  int sum (int a, int b){
    return a + b;
  }
  print("sum(10, 3) = ${sum(10, 3)}");
 
  // 7. 클래스(Class) 객체 생성 및 메서드 호출
  Person p = Person("홍길동", 20);
  p.sayHello();
  
  // 8. 비동기 처리 (Future: 미래에 완료될 작업, async/await: 작업할때까지 기다리기, then: 작업 완료 후)
  fetchData().then((value)=>print("then (1): $value")); //#1 fetchData() 2초타이머 / #2 then 예약
  testAsync(); // #3 fetchData() 다른 2초 타이머

}
// 비동기 함수 정의 (2초 후 완료)
Future<String> fetchData() async {
  await Future.delayed(Duration(seconds: 2));
  return "서버에서 데이터 가져오기 완료!";
}

void testAsync() async {
  String result = await fetchData();
  print("await: $result");
}

// 클래스 정의
class Person {
  String name;
  int age;
 
  Person(this.name, this.age);

  void sayHello() {
    print("안녕하세요, 저는 $name이고 나이는 $age살입니다.");
  }
}


 
