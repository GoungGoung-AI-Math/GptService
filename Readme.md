# 프로젝트 시작 방법

1. [apikey.properties.local](src/main/resources/apikey.properties.local)을 `apikey.properties`로 수정
2. `openai.api-key`에 OPENAI API KEY를 작성
3. `gradle build` 실행
4. `gpt4j-0.0.1-SANPSHOT.jar` 실행
5. `http://localhost:8080/math-teacher`에 Post 요청

## 학생 이미지, 기출 문제, 공식 풀이를 전달

이미지는 png, jpeg 모두 가능합니다.

```json

{
    "contents":[
        {
            "type":"text",
            "text" : "Analyze the student’s solution and compare it with the correct solution. The solution that includes the ‘출제의도’ is the formal solution."
        },
        {
            "type":"image_url",
            "image_url" : {
                "url" : "https://~~{온라인으로 접근 가능한 이미지}"
            }
        },
        {
            "type":"image_url",
            "image_url" : {
                "url" : "https://~~{온라인으로 접근 가능한 이미지}"
            }
        },
        {
            "type":"image_url",
            "image_url" : {
                "url" : "https://~~{온라인으로 접근 가능한 이미지}"
            }
        }
    ]
}
```