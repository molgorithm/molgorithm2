<img src="documents/molgorithm_banner.png" alt="banner_image" style="zoom: 25%; text-align: center;" />

# 모르고리즘.ver2



## 1. 스터디 규칙

### 🕑 시간

매일 13:00 ~ 16:00(3시간)에 모여 스터디를 진행할 예정입니다.

해당 날짜에 참여를 못할 경우 미리 **discord 출석부**에 `불참일시`를 남겨 놓으시면 됩니다. 

### 💌 문제 선정

코테 준비를 위해 카카오 기출 문제 풀이를 진행할 예정입니다.

추후에 함께 풀고 싶은 문제가 있다면 **discord 문제 추천방**에 `문제링크`를 올려 놓으시면 됩니다.

### 💻 방식

1. 자신의 계정으로 해당 repository를 fork 합니다.
2. 문제 풀이 후 convention에 맞춰 commit 후 해당 repository로 pull request를 하시면 됩니다.

**Repository로 pull request하는 양식 등 여러 양식에 관한 사항들은 convention에서 자세히 다루겠습니다 :)**



## 2. convention

저희는 다음과 같은 convention을 지키는 것을 지향합니다 :)



### ✅ Code Convention

✔ 변수와 함수 이름은 어떤 역할을 하는지 알 수 있도록 붙입니다. (a, b와 같은 단순 문자 지양, 파이썬의 경우 [PEP 8 스타일 가이드](https://www.python.org/dev/peps/pep-0008/) 준수 지향)

**✔ code 마지막 줄에는 한 줄을 비웁니다. (git add + git commit 하기 전에 확인 해주세요 😊)** 



### ✅ Readme Convention

README의 경우 다음 형식을 지향합니다.

```
<h1> 문제 제목 </h1>
문제 링크
<h2> 1. 설계 로직 </h2>
<h2> 2. 코드 </h2>
<h2> 3. 후기 (고민한 부분, 삽질한 부분와 같이 문제에 대한 후기) </h2>
```



### ✅ Commit Convention

**한 번에 `git add .` 하는 것보다 commit type에 맞게 분리하는 걸 지향합니다.**

```
docs: README.md 등 문서 작성 및 수정
code: 코드 작성
fix: 코드 수정
add: 기존에 푼 문제에 대한 또 다른 솔루션 코드 추가
merge: 내 레포에서 올린 pull request를 현재 organization의 algorithm-study 레포에 합치기
```

- commit type이 `code`인 경우, commit message에는 다음과 같은 정보를 명시하는 걸 지향합니다.

```
git commit -m "code: 본인영어이름 문제플랫폼 문제번호 문제이름"
```

예시는 다음과 같습니다.

#### 상황 예시 

**김철수**라는 사람이 있습니다. **철수**의 영어이름은 **sky**입니다. **철수**는 백준에서 다이나믹 프로그래밍 유형인 1000번을 풀이했습니다. 

1. 우선 코드를 하나의 커밋으로 분리합니다.

   ```
   git add boj1000.py
   git commit -m "code: sky boj 1000 A+B"
   ```

2. 코드에 대한 설명을 작성한 문서를 하나의 커밋으로 분리합니다.

   ```
   git add README.md
   git commit -m "docs: sky boj 1000 A+B"
   ```



### ✅ Review Convention

> 한 개의 Pull Request에는 1개의 문제 관련 commit만 추가하는 것을 지향합니다.

#### [Pull Request]

##### 🔖 제목

- Pull Request를 작성할 때 제목에는 "본인영어이름 문제플랫폼 문제번호 문제이름"을 작성하는 것을 지향합니다.

  ```
  sky BOJ 1000 A+B
  ```

##### 🔖 메시지(내용)

- Pull Request를 작성할 때 메시지에는 "본인이 작성한 README.md의 내용"을 추가하는 것을 지향합니다.

##### 🔖 Label

- 자신이 푼 **문제 유형**을 자신의 pull request에 label을 붙입니다.

##### 🔖 Assignee

- 자신의 pull request의 assignee에 자신을 추가합니다.

##### 🔖 Reviewer

- 같은 언어를 주로 사용하는 사람

- reviewer의 경우, 자신의 의견을 망설이지 않고 표출해주세요 !

  (ex. 이 코드에서 좋았던 점, 개선점, 이모티콘 등등 자유롭게 가능합니다.😊)

##### 상황 예시

> 기존에 pull request를 작성했지만 새로운 문제를 풀었을 경우, 새로운 문제에 대한 commit을 하기 전 다음과 같은 과정을 수행하는 것을 지향합니다.

**상황 1. 자신의 PR에 대해 적절한 리뷰를 받았다고 생각한 경우**

1. 해당 organization의 repository의 github issue에 자기가 푼 문제에 대한 issue를 생성합니다. 

   issue 제목에는 "본인영어이름 문제플랫폼 문제번호 문제이름"을 작성하는 것을 지향합니다.

   ```
   sky BOJ 1000 A+B
   ```

2. 자신이 작성했던 pull request에서 초록색 merge버튼을 눌러 merge합니다.

3. 자신이 작성했던 pull request에서 issue로 앞서 생성한 이슈를 연결합니다. 이슈는 닫히지 않았는지 확인합니다.



**상황 2. 새로운 문제를 풀 경우**

1. 자신의 로컬에서 clone한 폴더에서 새로 푼 문제에 대한 브랜치를 생성합니다. (git checkout과 branch 이용) 브랜치 이름은 다음과 같은 이름을 지향합니다.

   ```
   git checkout -b 본인영어이름/feature/문제플랫폼-문제번호
   ```

   ```bash
   Jo@Jo-LAPTOP MINGW64 /c/documents/algorithms/boj/ (main)
   git checkout -b sky/feature/boj-1000
   
   Jo@Jo-LAPTOP MINGW64 /c/documents/algorithms/boj/ (sky/feature/boj-1000)
   ```

2. 새로운 문제에 대한 code와 README.md에 대한 commit을 추가하고 push합니다. 이 때 반드시 터미널에서 브랜치 이름이 지정되어있는지 확인합니다.

   ```bash
   Jo@Jo-LAPTOP MINGW64 /c/documents/algorithms/boj/ (sky/feature/boj-1000)
   git commit -m "code: sky boj 1000 A+B"
   
   Jo@Jo-LAPTOP MINGW64 /c/documents/algorithms/boj/ (sky/feature/boj-1000)
   git commit -m "docs: sky boj 1000 A+B"
   
   Jo@Jo-LAPTOP MINGW64 /c/documents/algorithms/boj/ (sky/feature/boj-1000)
   git push
   ```

3. 본인 계정에 있는 algorithm-study repository에서 pull request를 작성할 때, 자신이 push한 브랜치(sky/feature/boj-1000)를 현재 organization의 레포의 브랜치(main)에 pull request에 보냅니다.



#### [Issue 등록]

##### 🔖 제목

- Issue를 작성할 때 제목에는 "본인영어이름 문제플랫폼 문제번호 문제이름"을 작성하는 것을 지향합니다.

  ```
  sky boj 1000 A+B
  ```

##### 🔖 메시지(내용)

- Issue의 메시지에는 아무것도 적지 않습니다.

##### 🔖 Label

- 자신이 푼 **문제 유형**을 자신의 pull request에 label을 붙입니다.

##### 🔖 Assignee

- 자신의 pull request의 assignee에 자신을 추가합니다.



#### [Merge]

##### 🔖 commit convention

```
merge: sky boj 1000 A+B
```

##### 🔖 내용

```
sky boj 1000 A+B
```

##### 🔖 Label

- 자신이 푼 **문제 유형**을 자신의 pull request에 label을 붙입니다.

##### 🔖 Assignee

- 자신의 pull request의 assignee에 자신을 추가합니다.



## 3. 가입 문의

가입 문의는 [네이버 폼](http://naver.me/FlJw2QCl)을 이용해주세요.



## 4. 건의 사항

건의 사항은 **discord 건의사항**에 남겨주세요.