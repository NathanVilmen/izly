# Izly: the student must-have on campus

## 0. Project goal

The goal of this project was to pick an existing application on the Application Store, one that I previously spotted as uneffecient or improvable, and improve some of its features.

## 1. What is Izly and why did I choose this application?

I was at the time a full time student. So during my day to day life I was using several mobile applications, whether it was for consulting my emails, or booking a time slot for a sport session.
But the application I was using the most was by far Izly.

Izly is a french application developed for students mostly, but also for all the university staff working on campus.
The app enables to access information regarding the life on campus and to pay for food, drinks or meals either at vending machines or at the university restaurant. Usually linked to the students' card, it key features are:
- Consult money balance for student account
- Top up your account
- Consult payments and top-ups history
- Wire money to personal bank account
- Pay at the restaurant
- Manage your account
- Consult campus news

The reason I chose this application is because as a user I spotted a lot of areas of improvement when using the application, especially regarding the user experience.

### A. The connexion page
<img src="https://github.com/NathanVilmen/izly/assets/132737190/f1dccd85-f24c-479a-9abf-19085167647c" alt="Izly_connexion_page" width="200"/> <br/>
In this connnexion page I clearly identifiied potential for more clarity. To me it was not aesthetic so I wanted to make it clearer and nice to see. <br/>
It as too important as the first page of the application not to reshape it.

### B. The navigation menu
<img src="https://github.com/NathanVilmen/izly/assets/132737190/d50db882-7ee4-46cd-86f4-24b7ef8aedac" alt="Izly_navigation_menu_focus" width="200"/> <br/>
This is the main page of the application, displaying the student account balance. <br/>
The navigation menu was not "user friendly" enough for me; it was not clear for me what menu item to go to for certain actions. <br/>
One page for balance, one to top up and one to pay is just too much. I wanted to combine the main page and give the ability to pay from this page.

### C. Unintuitive and unnecessary actions
<img src="https://github.com/NathanVilmen/izly/assets/132737190/60aa5870-d2fd-4d31-beff-35d39e36b898" alt="Izly_deconnexion_focus" width="200"/> <br/>

It is here in the `Plus` button that we could see the sign off option, at the bottom of the actions list. I wanted it to be more accessible. <br/>

<img src="https://github.com/NathanVilmen/izly/assets/132737190/ba630e89-f9dc-4154-bc1b-8e336742c156" alt="Izly_hidden_button_focus" width="200"/> <br/>
Same here, to manage our credit cards, we need to go to `Plus`, then `Preferences`, then `Manage my cards`. What a mess!

### D. Hidden buttons

The last screenshot illlustrates well the hidden <b>Back</b> buttons, small and grey on a blue background. Just impossible to see on a mobile screen. <br/>

## 2. Database

To address this project I chose to use Java and Room. I focused on the payment features, as the others were less used on a daily basis. <br/>
I created 3 tables, with in <span style="color:green">green</span> the primary key and in <span style="color:yellow">yellow</span> the foreign key(s). <br/>

### `table_rechargement`
<img src="https://github.com/NathanVilmen/izly/assets/132737190/ed3e26cd-3ee5-42f3-9852-2a4520bf15dc" alt="table_rechargement" width="400"/> <br/>
In this table I stored the transactions. Whether they were payments or top ups, they were differenciated by their sign (`+` or `-`). <br/>

### `carte_table`
<img src="https://github.com/NathanVilmen/izly/assets/132737190/689726df-19c5-45e9-ad3b-9c1509d9be53" alt="carte_table" width="400"/> <br/>
In this table I stored the credit cards and their details. <i>I deliberately chose not to encode passwords and sensitive data, as it just need to go through a Hash and I did not want to miss the point of my project by spending too much time on this.</i>

### `table_utilisateur`
<img src="https://github.com/NathanVilmen/izly/assets/132737190/4389ee9b-0fb7-4df2-ba74-f6e1aeff030a" alt="table_utilisateur" width="1000"/> <br/>
In this table I stored all of my users. In most features it was my entry point to figure out at all times what user was connected. <i>Same here for password encoding.</i> <br/>

## 3. Design Pattern Choice
<img src="https://github.com/NathanVilmen/izly/assets/132737190/c566d806-b67f-4e38-aee4-9762d255159f" alt="carte_table" width="400"/> <br/>
For this project I used the `MVVM`design pattern (Model-View-ViewModel).
It is clear and enables a separation between responsibilities. The live update and link to database was also very useful.

## 4. Mockups
- For the menu, I removed the `Payment` page
- In the main page, I chose to add the `Payment` feature, that displayed the QR Code to pay at the restaurant
- I renamed the last menu item into `Preferences`(Paramètres en français), where the user has directly access to main actions
- I reshaped the `Top up` page to make it more efficient.
<br/>

![4_maquettes](https://github.com/NathanVilmen/izly/assets/132737190/f8e76594-aa84-4cc9-ab11-4565bbc006de)

## 5. Result - Main pages
![4_premiers_rendus](https://github.com/NathanVilmen/izly/assets/132737190/921445cb-089e-45a3-bd29-fa0d51753bb2)
![4_rendus_secondaires](https://github.com/NathanVilmen/izly/assets/132737190/254d97ae-a7ee-49d9-a950-2dff93c56111)

## 6. Next steps

The next steps would be for me to:
- Add last features, sich as the feature listing the campus news
- Improve the design
- Reinforce security through code inspection and mostly by encoding passwords and credit card details.

`Thank you for taking the time to read me and discover my project!`
