### Shop24
BPR Internship Bk Challenge

## Introduction to Shop24

### Who is Shop24?
Shop24 is a soft drinks wholesaler based in Kigali. They would like to avail their services to public by providing a white-label solution to the
existing retailer companies/hotels. Practically, they will build a set of APIs that hotels will use to manage their requests of buying from
Shop24 and transport to their clients. They would like your help building these APIs and document them using swagger

### Technology constraints
• At the Bank of Kigali, we currently have a microservices architecture with services mostly written in Spring Boot (Java or Kotlin).

• We would like you to develop a single service in either Spring Boot that exposes several REST endpoints (see next page)

• You can use whatever database you want (we recommend using SQLite, MySQL or Postgres)

• You should include a README file that has instructions for us to get the solution running on our machines

## What are we looking to test?
• The overall software architecture of the application
• The structure and quality of the code itself
• The use of well-known patterns for REST and Sprint development
• Your ability to model the problem domain (data models and APIs)
• Your ability to document APIs
• Bonus points if you include tests in your solution

## What are we not looking to test?
• We don't expect you to implement authentication or authorization

## API Requirements for Shop24

### DRINKS
• Get a list of all drinks.

• Get a list of most consumed drinks and quantity.

• Get a list of all available drinks and nearest cargo company to the client within 3km based on the client's location.

• Get a specific drink by ID.

### ORDER
• Create a new 'order' request by assigning a list of drinks to a specific client.

• Choose top five orders that were requested by different clients.

• Get top 10 paid orders, their client details, and transporter details.

• Complete an order.
