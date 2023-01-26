책 <점프 투 자바>를 공부하며 코드 및 개념 정리

.

.

.

* **실습 1 관련 공부** (https://blog.naver.com/miso324/222990036190)


  - keywords: 자바 / 컴파일 / .class 파일 / .java 파일 / 패키지 / package / public static void main(String[] args)

.

.

.

**01. main 메서드**

```
// Main.java

public class Main {
    public static void main (String[] args) {
        ...
    }
}
```

Main.java 파일을 실행하면, Main 클래스 안에 있는 main 메서드가 호출된다. 프로그램이 실행되면 해당 프로그램의 main 메서드가 실행된다는 것이 자바의 규칙이다. 또한 main 메서드가 종료되는 것은 프로그램이 종료됨을 의미한다. (외부 클래스에서 해당 클래스를 호출했을 경우 main 메서드가 자동으로 실행되지는 않는다. 파이썬에서 if __name__ == "__main__": 과 같은 역할을 하는 부분으로 보인다.) 그리고 해당 main 메서드는 위와 같이 public static void main (String[] args)로 시작되어야만 한다. 여기에는 이유가 있다.



  (1) public

    - public 메서드는 모든 클래스에서 호출 가능한 메서드를 의미한다.

    - <-> private: 해당 클래스 / default: 해당 패키지 / protected: 해당 패키지 혹은 상속 클래스에서만 호출 가능

    - main 메서드는 프로그램 외부(=패키지 외부=클래스 외부)에서 호출되므로 어디에서든 호출가능한 public으로 지정한다.



  (2) static

    - static 메서드는 클래스의 인스턴스 객체가 만들어지지 않아도 호출 가능하며 메모리의 static 영역에 저장된다.

    - 메모리의 static 영역은 프로그램의 시작부터 종료까지 유지된다.

    - <-> 메모리의 heap 영역: 인스턴스 등이 저장되며 Garbage Collector가 주기적으로 청소한다.

    - main 메서드는 프로그램이 실행되는 도중에 (Garbage Collector에 의해) 삭제되어서는 안되므로 메모리의 static 영역에 저장되는 static으로 지정한다.



  (3) void

    - void 메서드는 return 값이 없는 메서드를 의미한다.

    - <-> String, int, boolean 등 return 값의 자료형을 지정하고 해당 자료형의 return 값이 있는 메소드

    - main 메서드가 종료되면 프로그램이 종료되므로 return 값이 의미가 없기 때문에 return 값이 없는 void로 지정한다.



  (4) (Strings[] args)

   - 메서드의 인자로 문자열의 배열을 받는 것을 의미하며, 해당 문자열이 메서드 내에서 args라는 변수로 사용될 것을 의미한다.

    - args 대신 다른 변수명을 사용해도 상괸 없으며, String args[]도 사용 가능하다.

    - 흔히 환경변수라고 하는 프로그램 외부에서 입력하는 값을 받기 위한 부분이다.






**02. package는 왜 적용되지 않았을까?**

```

// \Files\Main.java
import testpackage.programs.*;

public class Main {
    public static void main(String[] args) {
        Geehyun geehyun = new Geehyun();
        System.out.println(geehyun.cry());
    }
}

// \Files\myPackage\programs\Geehyun.java
package testpackage.programs;

public class Geehyun {
    public String cry() {
        return "엉엉";
    }
}

*** shell ***
java Main.java
```

shell에 위와 같은 명령어를 입력하면 '엉엉'이라는 말이 출력되어야 하는데 되지 않았다. 배운대로 패키지를 설정했는데, Main 클래스가 Geehyun이라는 symbol을 찾지 못했다는 문구만 계속 등장했다. 자바에서 해당 symbol을 찾지 못했다는 문구는 해당 변수가 선언되지 않았음을 의미하는 아주 흔한 오류 문구기 때문에 그 원인을 찾는데 굉장한 시간을 소비했다. 유사 카페모카로 당과 카페인을 충전한 후에야 원인을 찾았는데, 몇시간 전 내가 무심하게 삭제한 .class 파일 때문이었다.



  (1) 자바 프로그램 실행 과정

    1. 인간이 ~.java 파일에 java 언어로 소스 코드를 프로그래밍한다.

    2. Java 컴파일러가 .java 파일을 컴파일하여 기계어(바이트 코드)로 된 ~.class 파일을 만든다.

    3. JVM이 ~.class 파일을 읽고 프로그램을 실행한다.

  (2) => 필요한 사실

    프로그램 실행에는 ~.class 파일이 필요하다.

    (Java가 빠른 이유는 미리 컴파일한 ~.class 파일을 실행여 컴파일 과정이 생략되기 때문이다.)

    class 파일이 있다면~.java는 프로그램 실행 단계에서 없어도 된다.

    ~.java 코드는 프로그램의 소스 코드이기 때문에, 보안을 위해 삭제하는 경우가 많다.

  (3) 다시 보는 package의 정의

    package는 클래스를 모아 놓은 디렉토리이다.

    여기서 클래스는 ~.class 파일을 의미한다.

    다시 말해, package는 ~.class라는 물리적 파일의 물리적 폴더이다.

  (4) ~.class 파일 만드는 명령어

    javac Geehyun.java

    Geehyun.java 파일에 오류 여부를 검사한 후 현재 디렉토리(package)에 Geehyun.class 파일이 생성한다. (실행X)

    생성 후 Geehyun.java가 수정된 것은 자동으로 반영되지 않으므로 재생성해야 한다.

    <-> java Geehyun.java: Geehyun.java를 실행한다. (.class 파일은 실행 불가)​

  

~~ 해결 ~~
```

*** shell ***
~/practice-to-use-JAVA/myPackage/programs$ javac Geehyun.java
~/practice-to-use-JAVA$ java Main.java
엉엉
```

.

.

.

.

.

* **실습 2 관련 공부** (https://blog.naver.com/miso324/222994980188)

  - keywords: JUnit, 점프투자바 실습2 오류, classpath, java with replit

.

.

.

**JUnit4**


JUnit은 Java의 라이브러리 중 하나이며, 테스트 케이스를 만들고 테스트를 실행하기 때문에, TDD(테스트 주도 개발)에서 유용하게 쓰인다. 라이브러리를 사용하기 위한 기본 틀이 있는데, 버전마다 사용 방법이 많이 다르다. 점프투자바 실습 2에서는 버전 3을 다루고 있으며, 최근 다양한 기능이 추가된 버전 5가 업데이트 되었다. 가장 최신인 버전 5를 공부하면 좋겠지만, 버전 5에 대한 설명이 기존 버전 4보다 좋아진 점 위주라, 버전 4로 된 가장 단순한 테스트가 정상 작동 되는 걸 목표로 하루를 보냈다.


```
// JUnit 3 ( = 점프 투 자바 코드 검색 결과가 잘 나오지 않는 이유)
// junit.framework 패키지 밑에 상주
import junit.framework.TestCase;

// JUnit 4
// org.junit 패키지 밑에 상주
import org.junit.Test;
```


(1) 패키지 설치하기

![image](https://user-images.githubusercontent.com/97844267/214757270-0a292434-e4f3-4ba4-b59a-f900a34cc421.png)

    - 설치하면 원하는 디렉토리에 target/dependency 디렉토리가 생기고 그 안에 두 개의 .jar 파일이 저장되어 있다.

    - 또한 pom.xml 파일이 생긴다. 이는 maven 이라는 빌드 툴의 특징인데 다음에 정리해야지.

    - 아무튼 maven의 다양한 기능은 https://mvnrepository.com/ 이 사이트 검색해서 나오는 코드를 pom.xml 파일에 적으면 쓸 수 있다.


(2) 컴파일

```
java -classpath .:/home/runner/studying/target/dependency/junit-4.13.2.jar:/home/runner/studying/target/dependency/hamcrest-core-1.3.jar Main
```
.java 파일을 .class 파일로 컴파일하는 명령어인 javac의 대표적인 옵션으로 -classpath와 -d가 있다.

  - classpath (=cp)

    - 해당 파일을 컴파일할 때 필요한 파일들(.class .jar 등)의 위치를 지정하는 옵션이다.

    - :로 구분하여 여러 경로를 참조경로로 이용할 수 있다.

    - 즉, 위 코드는 현재 디렉토리(.)와 위에서 설치 시 설치된 두 개의 .jar 파일을 참조하겠다는 것이다.

  - d

    - 컴파일 결과(.class 파일)를 저장할 위치를 지정하는 옵션이다.

    - target/classes 안에 주로 저장하는 것 같지만, 나는 편의상 현재 디렉토리(.)에 저장했다.

    - 그 뒤는 컴파일 대상 파일이다. 여러개를 나열하면 한번에 컴파일 가능하다.


(3) Main.class 실행

```
java -classpath .:/home/.../target/dependency/junit-4.13.2.jar:/home/.../target/dependency/hamcrest-core-1.3.jar Main
```
    - classpath: javac의 classpath와 같다.

    - java 명령어에 파일 확장자(.java .class 등)를 붙이지 않으면 .class 파일이 실행된다.



+) 사실 Tools 의 unit Tests 버튼을 이용하면 이러한 과정 없이 테스트 케이스 생성이 가능하다



![image](https://user-images.githubusercontent.com/97844267/214757541-e0257020-e5e2-4199-aa09-8c0fec92bc06.png)




