# konggaru
<img src="https://img.shields.io/badge/Oracle-F80000?style=flat-square&logo=Oracle&logoColor=white"/> <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=HTML5&logoColor=white"/> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=Spring&logoColor=white"/> <img src="https://img.shields.io/badge/AWS S3-569A31?style=flat-square&logo=AmazonS3&logoColor=white"/> <img src="https://img.shields.io/badge/Vue.js-4FC08D?style=flat-square&logo=Vue.js&logoColor=white"/>
<img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=CSS3&logoColor=white"/> <img src="https://img.shields.io/badge/jQuery-0769AD?style=flat-square&logo=jQuery&logoColor=white"/> <img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white"/> <img src="https://img.shields.io/badge/Bootstrap-7952B3?style=flat-square&logo=Bootstrap&logoColor=white"/> <img src="https://img.shields.io/badge/Eclipse IDE-2C2255?style=flat-square&logo=Eclipse IDE&logoColor=white"/> <img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=flat-square&logo=IntelliJ IDEA&logoColor=white"/>  

> 스프링 기반 카페 관리 시스템 및 클라이언트 프로젝트

에이콘 아카데미 최종 프로젝트로 진행되어 수업 시간에 배운 내용을 기반으로 적용 및 응용하며 개발 과정을 충분히 이해하는 것을 목표로 진행하였습니다.

## Team
| 이름 | 역할 |
|:---:|:---|
| 강현구 | 상품 관리 및 클라이언트 구현, 상품 이미지 S3 연동 |
| 김시은 | 상품 관리 및 클라이언트 구현 |
| 김상범 | 재고 관리, 매출 확인, 상품 순위, 로그인, 회원가입 구현 |
| 박찬양 | 재고 관리 구현 |
| 배선영 | 직원 관리 구현 |
| 배선영 | 직원 관리 구현 |

팀은 전공자 3명과 비전공자 3명으로 구성되어 전공자와 비전공자 1명씩 기능을 맡아 해당 프로젝트를 진행하였습니다. 또한, Github, Notion을 사용해 협업 및 이슈를 관리하였습니다. 
기능 별로 브랜치를 생성하여 develop 브랜치로 통합 후 master에 푸쉬하도록 진행했습니다. develop으로 풀리퀘스트 시 가급적이면 코드리뷰 후 머지할 수 있도록 했습니다.

## Features
- 상품 조회, 추가, 수정, 삭제, 주문
- 재고 조회, 추가, 수정, 삭제, 주문
- 직원 조회, 추가, 수정, 삭제, 출근부
- 로그인/아웃, 고객 회원가입, 회원 정보 수정
- 매출 확인, 상품 순위
- 장바구니 조회, 추가, 수정, 삭제

## Skills
- Backend
    - Spring MVC, Spring Security, Hibernate Validator, Maven, MyBatis
- Frontend
    - jQuery, Vue.js, Bootstrap
- Database
    - Oracle, S3
- Tool
    - Intellij IDEA, Ecplise, Notion, Git, Github

## ERD
- Client

![client-erd](https://user-images.githubusercontent.com/48203569/128145276-5a095082-c050-4a84-8eb1-0a6b297acb5e.png)

- Admin

![admin-erd](https://user-images.githubusercontent.com/48203569/128145272-e0d1b887-3afa-41b9-bc8a-6353380cd729.png)

## Screen flow
![konggaru-flow](https://user-images.githubusercontent.com/48203569/128145518-efb8e66b-e7d6-4d96-b96a-1d866318efb6.png)

## Screens
- 클라이언트

||||
|:---:|:---:|:---:|
|![home][home]|![details][details]|<img src="https://user-images.githubusercontent.com/48203569/128148506-a2f4bde1-ea96-4032-b505-30facf25032d.jpg" width="300px"/>|
|![cart][cart]|![checkout][checkout]|![order-history][order-history]|

- 상품 관리

||||
|:---:|:---:|:---:|
|![new-product][new-product]|![edit-product][edit-product]|![product-list][product-list]|

- 재고 관리

||||
|:---:|:---:|:---:|
|![new-ingredient][new-ingredient]|![ingredient-order][ingredient-order]||

- 직원 관리

||||
|:---:|:---:|:---:|
|![staff-list][staff-list]|![edit-staff][edit-staff]|![rollbook][rollbook]|

- 매출, 로그인/아웃

||||
|:---:|:---:|:---:|
|![dashboard][dashboard]|![login][login]|![signup][signup]|

## Libarary used
- [SweetAlert](https://sweetalert.js.org)
- [FontAwesome](https://fontawesome.com)
- [jQuery](https://jquery.com)
- [Bootstrap](https://getbootstrap.com)

[home]: https://user-images.githubusercontent.com/48203569/128146147-5c094f3f-e2da-41f5-a2ab-23ab64360fb5.png
[details]: https://user-images.githubusercontent.com/48203569/128146152-ebf5964d-e00d-42ca-832c-76434850ddbd.png
[cart]: https://user-images.githubusercontent.com/48203569/128146157-08595ca1-000e-4a9e-8237-e0c3d074e4e7.png
[checkout]: https://user-images.githubusercontent.com/48203569/128148646-fe8c4459-f626-40c1-b5b6-074f5b4216c2.png
[order-history]: https://user-images.githubusercontent.com/48203569/128148511-eda2528a-7f0e-4d2c-93f4-3d03e4c98cf2.png
[my-page]: https://user-images.githubusercontent.com/48203569/128148506-a2f4bde1-ea96-4032-b505-30facf25032d.jpg
[rollbook]: https://user-images.githubusercontent.com/48203569/128180199-eed1066f-346c-4b60-8273-97b641c13f5f.png
[edit-staff]: https://user-images.githubusercontent.com/48203569/128180196-e9a23c80-e67a-4908-bbaf-66957124a78d.png
[staff-list]: https://user-images.githubusercontent.com/48203569/128180194-3979b7dc-3453-4da4-8000-7b4391f37820.png
[ingredient-order]: https://user-images.githubusercontent.com/48203569/128180090-b78edc6d-981d-4928-b1d0-c5e6342ca5ab.png
[new-ingredient]: https://user-images.githubusercontent.com/48203569/128180086-e1cd4777-9f53-41e6-b64c-55f2cd90ee9e.png
[new-product]: https://user-images.githubusercontent.com/48203569/128180927-06e7b9f9-1b1a-40b0-a451-97ffaabfdefe.png
[edit-product]: https://user-images.githubusercontent.com/48203569/128180946-6ee47746-fedd-4f14-8293-4ecc2ae581bc.png
[product-list]: https://user-images.githubusercontent.com/48203569/128181121-dc850126-f223-4bee-b41b-85f1d3f7e47a.png
[dashboard]: https://user-images.githubusercontent.com/48203569/128182374-d6e23770-d74e-4ed4-88e5-0af89c995340.png
[login]: https://user-images.githubusercontent.com/48203569/128182164-03eff241-6add-45e1-9058-424024970ca1.png
[signup]: https://user-images.githubusercontent.com/48203569/128182091-73587d1e-cbee-4d2c-abf2-c695375d64d7.png
