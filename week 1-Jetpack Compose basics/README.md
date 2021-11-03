# ComposeFest2021
Week1 Jetpack Compose Basic 코드랩은 새 프로젝트를 만드는 것으로 시작합니다.

## 작업 전 필수 확인
Basics에 올려둔 코드는 테마 관련 코드 확인용입니다.

- 꼭 새 프로젝트를 만들고 시작하세요.
- 본 코드의 내용은 참고용으로 활용하세요.

## 작업 후
직접 생성한 프로젝트를 여기에 붙여 넣어주세요.
Git 명령어 또는 [SourceTree](https://www.sourcetreeapp.com/), [GitKraken](https://www.gitkraken.com/) 등을 이용해 작업 결과를 push 해주세요.

### Compose

요즘 앱들은 데이터가 동적이고 실시간으로 업데이트 된다. 원래 android views를 사용하면 xml에 ui를 선언해야하고 데이터가 바뀌면 UI도 업데이트를 해야한다. 이를 위해서는 View를 조회하고 속성을 설정해야 한다. 따라서 애플리케이션 상태가 바뀔때마다 새로운 정보로 UI를 업데이트해서 데이터 동기화를 해줘야된다는 것이다. view는 view마다 상태가 다 달라서 각각 업데이트하는 과정이 복잡할 수 있고 모델과 UI를 동기화하는 작업에서 버그가 엄청나게 발생할 수 있다.

Compose는 선언적 UI로 기존 View와는 다른 방식을 사용한다. 간략하게 말하면 상태를 UI로 변환하는 방식을 사용한다.

![img1](/Users/ckg/AndroidStudioProjects/ComposeFest2021/week 1-Jetpack Compose basics/image/img1.png)

UI는 변경할 수 없고 한 번 생성하면 업데이트가 불가능하다. 따라서 앱 상태가 바뀌면 새로운 상태를 새로운 UI로 변환한다.

![img2](/Users/ckg/AndroidStudioProjects/ComposeFest2021/week 1-Jetpack Compose basics/image/img2.png)

UI전체를 다시 생성하는 과정으로 동기화 문제가 완전히 해결된다. 
Compose는 매우 지능적이여서 변경되지 않은 요소에 대한 작업은 건너뛴다. 하지만 개념적으로 특정 상태에 맞추어 UI를 새로 생성하는 것과 같다. 코드는 특정 상태에 대한 UI형태를 설명할 뿐이고 생성 방법은 지정하지 않는다.

#### coerceAtLeast ?? 

11번째 스텝인 커스텀 애니메이션부분에서 coerceAtLeast라는 함수가 나왔다. 이를 지정해주지 않았을 때bouncy 애니메이션이 일어나면  expand된 카드가 접히고 튕기는 과정에서 padding값이 음수로 가면서 앱이 터진다. 이를 방지해주기 위해 최소 padding값을 지정해주는 함수인 것 같다.

![img3](/Users/ckg/AndroidStudioProjects/ComposeFest2021/week 1-Jetpack Compose basics/image/img3.png)

 
