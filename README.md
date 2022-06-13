# Journal-Search-Capstone
Capstone Project :  
Team ID : C22-PS147 
Member :  1. Aldiana Sugesti Firdayanti - C2193F1842 
          2. Reza Maharani Susilo - C2012F1315 
          3. Elva Bunga Mandira Arifianti - M7312F2710 
          4. Novalanza Grecea Pasaribu - M2012F1323 
          5. Ramdhani Al Sulaiman - A2314F2730 
          6. Baihagi Muhammad Surya - 
          
          
          
# Cloud Computing
The considerations we use when deciding to choose an app engine is that because app engines are included in the platform as a service, getting a service provides more instances when traffic is high and decreases instances when traffic decreases automatically. 

# Cloud Services Architecture
![image](https://user-images.githubusercontent.com/101315797/173280854-7158f77f-6ed1-46e6-89bf-9bf53758eb9f.png)

# Documentation Deployment
1. Create a new project in google cloud console

![image](https://user-images.githubusercontent.com/101315797/173280951-c139b056-2be3-4687-9a80-935ed0b61c41.png)

2. Set the IAM to invite another member

![image](https://user-images.githubusercontent.com/101315797/173281034-b2231e86-4e0f-4899-911a-daca15d66930.png)

3. Create instance on CloudSQL choose MySQL

![image](https://user-images.githubusercontent.com/101315797/173281102-c6f8ee65-9e58-4dc5-a1db-44a31e1ed6a8.png)
When you create the instance, you generate the instance ID and password, save the id and password for later use. And don’t forget to make a connection as a Public IP

4.Create the database inside the instance that we create previously

![image](https://user-images.githubusercontent.com/101315797/173281159-f1fe86ae-5e9c-421c-bdb0-be7ba91589a2.png)

5. Import the database you use from local to cloudSQL, import to database that we create previously. 
6. Enable App engine and API
7. Clone the code from this github to your code editor
8. Install and init google cloud SDK
9. Change the database credential with your credential

![image](https://user-images.githubusercontent.com/101315797/173281251-66848577-0b10-4ed7-b3a2-f9a497a5d0e0.png)

10. After set the credential and init google cloud SDK, we can deploy with app engine by running this code “gcloud app deploy”
11. After it deploy succesfully we can check the endpoint with postman

![image](https://user-images.githubusercontent.com/101315797/173281305-0217e2f9-cfeb-4fc7-8228-e83e299a2152.png)
![image](https://user-images.githubusercontent.com/101315797/173281351-ee14b719-753c-41c7-8ed6-5722dc27a47c.png)
![image](https://user-images.githubusercontent.com/101315797/173281368-349fecea-5856-48d3-83a5-18da0501d485.png)

We can check the POST method:
![image](https://user-images.githubusercontent.com/101315797/173281405-5ce86ba2-1c53-472d-b2e6-05d94d63e012.png)
Method to post new jurnal, and with ML model we can automatically chategorize that journal
![image](https://user-images.githubusercontent.com/101315797/173281428-737a3e33-1b8e-4795-a0cf-c0a9ececc829.png)


We can check the log activity from app engine
![image](https://user-images.githubusercontent.com/101315797/173281443-e5277753-88b7-4416-9810-2ba52812da56.png)






