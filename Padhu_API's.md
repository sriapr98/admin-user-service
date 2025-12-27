I want to create two JAVA springboot API's with mysql database.

Generate swagger and use docker for running mysql.

API details are mentioned below
1. Admin user details - /admin/details - POST - Store admin user details in db
    Request body
        - user_id
        - department(HR/Finance)
        - designation(Manager/Team lead)
        - product(Laptop/Monitor)
        - produce_code(P01,P02)
        - grade(A/B/C)
        - position(Junior/Senior/Lead)
    Response - 204 status code
2. Admin user details - /admin/details - GET - Retrieve admin user details from db
    Request body
        - user_id
    Response - 200 status code
        - department(HR/Finance)
        - designation(Manager/Team lead)
        - product(Laptop/Monitor)
        - produce_code(P01,P02)
        - grade(A/B/C)
        - position(Junior/Senior/Lead)