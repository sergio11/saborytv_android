# SaboryTV 🍳📺: Your Personal Chef, Right on Your TV

<img width="auto" height="200px" align="left" src="doc/main_logo.png" />

Welcome to **SaboryTV**, the ultimate platform to elevate your cooking experience directly from your Smart TV. Whether you're aiming to refine your culinary skills, explore new flavors, or simply follow along with top chefs, SaboryTV is designed to cater to your tastes and dietary goals. With an extensive collection of recipes, tailored to your preferences, you'll be able to create the perfect dish every time. 🍝🍰

With SaboryTV, you can watch expert chefs in action and follow their step-by-step instructions from the comfort of your kitchen. The platform offers an interactive, hands-free experience that guides you through every detail of the recipe—from ingredient prep to plating—so you can focus on perfecting your dish without distractions. 👨‍🍳👩‍🍳

**Built using Jetpack Compose for TV**, SaboryTV integrates seamlessly into your Smart TV environment. Utilizing [**🍮 Fudge**](https://github.com/sergio11/fudge_tv_compose_library), a powerful UI Kit for TV apps, SaboryTV ensures smooth navigation and a delightful user experience. Our platform is designed to make following along with cooking videos intuitive, engaging, and fun, turning your kitchen into a culinary playground. 🍽️✨

Happy cooking with SaboryTV! 🎉👨‍🍳

<p align="center">
  <img src="https://img.shields.io/badge/Android%20Studio-3DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white" />
  <img src="https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white" />
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/firebase-ffca28?style=for-the-badge&logo=firebase&logoColor=black" />
  <img src="https://img.shields.io/badge/Material%20UI-007FFF?style=for-the-badge&logo=mui&logoColor=white" />
</p>

## Overview 🌐

**SaboryTV** is not just a recipe guide—it's your ultimate culinary companion designed to enhance your cooking experience and accommodate the entire family. Here’s a look at what makes SaboryTV a standout platform:

### Personalized Profiles 👥
With SaboryTV, each family member can create their own profile, allowing everyone to save their favorite recipes and receive personalized recommendations. This feature ensures that everyone in your household can enjoy a tailored cooking experience based on their own tastes and dietary preferences.

### Advanced Recipe Search 🔍
Our advanced search functionality lets you find recipes based on various criteria, including:
- **Preparation Time** ⏳: Find recipes that fit your schedule.
- **Video Language** 🌍: Choose recipes with instructions in your preferred language.
- **Recipe Category** 📂: Explore recipes categorized as appetizers, main courses, sides, and desserts.
- **Recipe Type** 🥗: Filter by types such as Vegan, Vegetarian, Gluten-Free, and High Protein.

### Customizable Preferences ⚙️
SaboryTV offers a range of customization options to enhance your experience:
- **Default Video Resolution** 📺: Set your preferred resolution for streaming recipes.
- **App Language** 🌐: Select the language for the app interface.
- **Measurement System** 📏: Choose between Metric and Imperial units for your recipes.

With these features, SaboryTV ensures a seamless, user-friendly experience tailored to your cooking needs and preferences. Whether you're looking for new recipes, saving your favorites, or adjusting the app to fit your lifestyle, SaboryTV has got you covered. 🍳📺

## Technologies Used 🛠️

- **Kotlin**: The language of choice for developing Android applications, offering modern syntax and powerful features to streamline coding and enhance productivity. 🚀

- **Firebase Platform**:
  - **Firestore**: 🔥 A NoSQL cloud database that provides real-time data synchronization and offline support. It scales seamlessly with your app's needs, ensuring fast and reliable data retrieval. 📊✨
  - **Firebase Auth**: 🔐 Handles user authentication with ease, providing secure sign-in and user management capabilities. Supports various authentication methods, including email/password, social media logins, and more. 🛡️📱
  - **Firebase Storage**: ☁️ Stores and serves user-generated content like profile images and media files. Offers robust and scalable storage solutions with built-in security and easy integration with other Firebase services. 📸🎥

- **Coroutines**: 🌀 Simplifies asynchronous programming and manages background tasks efficiently. Ensures smooth, non-blocking operations, enhancing the app's responsiveness and user experience. ⏱️

- **Clean Architecture**: 🏗️ Promotes a well-structured and scalable app design by separating concerns into distinct layers. Enhances maintainability, testability, and overall robustness of the application. 🔍

- **MVI (Model-View-Intent)**: 📈 Implements a unidirectional data flow pattern, making state management predictable and consistent. Ensures a clear separation between UI components and business logic. 🔄

- **Jetpack Compose for TV**: 📺 Leverages Jetpack Compose to build modern, responsive UIs tailored for TV screens. Focuses on reusability and a seamless viewing experience, optimizing the interface for large displays. 🎨

- **Jetpack Compose Navigation**: 🗺️ Facilitates in-app navigation and screen transitions with a clear and concise API. Supports deep linking and helps manage complex navigation flows effortlessly. 🚦

- **Material Design 3**: 🎨 Applies the latest Material Design guidelines to create a visually appealing and intuitive user interface. Ensures consistency and enhances the overall aesthetic of the app. 🖌️

- **🍮 Fudge**: [Fudge](https://github.com/sergio11/fudge_tv_compose_library) is a Jetpack Compose UI Kit tailored for TV apps. It provides pre-built components and tools to craft engaging and seamless experiences on the big screen. Simplify your TV app development with Fudge! 🎬🚀

- **Jetpack DataStore**: 💾 A modern data storage solution for storing key-value pairs and typed objects. Provides a robust and asynchronous API for data management, ensuring consistent and reliable data handling in your app. 🔐

- **Media3 for Media Playback**:
  - **Media3 ExoPlayer**: 🎥 Part of the Media3 library, ExoPlayer is a powerful media player that supports a wide range of media formats and advanced features like adaptive streaming and DRM support. Ensures high-quality and smooth playback experiences. 📻🍿
  - **Media3 UI**: 🎨 Provides UI components and tools to integrate media playback controls seamlessly into your app's interface. Enhances the media playback experience with customizable and user-friendly controls. 🕹️

- **Dagger Hilt**: 🧩 A dependency injection library that simplifies the management of dependencies in your app. It reduces boilerplate code and enhances modularity by providing a clear and concise way to inject dependencies and manage their lifecycle. 🔧💡

- **Mapper Pattern**: 🔄 Facilitates conversion between different data models (DTOs, BOs, etc.), ensuring data consistency and smooth interactions across various application components. 📐

## Architecture Overview 🏛️

Our application is designed with a robust and scalable architecture to ensure maintainability, testability, and flexibility. The architecture leverages several design patterns and principles to create a well-structured and efficient system.

### **Clean Architecture** 🏗️
Clean Architecture is the foundation of our design, focusing on separating concerns into distinct layers. This approach enhances the maintainability and testability of the code by isolating business logic from the UI and data layers. Our architecture typically includes the following layers:
- **Presentation Layer**: Handles the UI and user interactions, using Jetpack Compose for building modern and responsive interfaces.
- **Domain Layer**: Contains the business logic and use cases. It defines the core functionality of the application and is independent of external frameworks.
- **Data Layer**: Manages data sources and repositories, providing a consistent interface for data access. It abstracts the details of data retrieval and storage from the rest of the application.

### **Data Sources** 📦
Data sources are responsible for fetching and managing data from various origins. We utilize multiple data sources, such as:
- **Remote Data Sources**: Interact with cloud services or web APIs (e.g., Firebase Firestore, Firebase Auth).
- **Local Data Sources**: Handle local data storage (e.g., Jetpack DataStore, SQLite).

### **Repository Pattern** 🗃️
The repository pattern provides a unified interface for accessing data, regardless of whether it's coming from a remote server or local storage. Repositories manage data operations and serve as a single source of truth for the application's data. This pattern decouples data retrieval and storage from the rest of the application, allowing for easier testing and maintenance.

### **Use Cases** 🧩
In the Domain Layer, **Use Cases** (also known as Interactors) represent specific actions or operations that the application can perform. They encapsulate business logic and interact with repositories to retrieve or modify data. Use Cases ensure that the business rules and application flow are managed correctly, providing a layer of abstraction that protects the domain logic from changes in the data or presentation layers. By focusing on specific tasks or operations, Use Cases contribute to the modularity and flexibility of the application.

### **Inversion of Control (IoC)** 🔄
Inversion of Control is a principle where the control flow of the application is inverted. Dependencies are injected rather than being hardcoded. This is achieved through:
- **Dependency Injection (DI)**: Managed by Dagger Hilt, DI simplifies the management of dependencies and their lifecycle, promoting modularity and reducing boilerplate code.

### **SOLID Principles** 📏
We apply SOLID principles to ensure our codebase remains clean, modular, and maintainable:
- **Single Responsibility Principle (SRP)**: Each class or module has one responsibility, reducing complexity and improving cohesion.
- **Open/Closed Principle (OCP)**: Classes and modules are open for extension but closed for modification, promoting flexibility and reducing the risk of introducing bugs.
- **Liskov Substitution Principle (LSP)**: Subtypes must be substitutable for their base types without altering the correctness of the program, ensuring proper inheritance hierarchies.
- **Interface Segregation Principle (ISP)**: Clients should not be forced to depend on interfaces they do not use, promoting more focused and cohesive interfaces.
- **Dependency Inversion Principle (DIP)**: High-level modules should not depend on low-level modules; both should depend on abstractions. This principle encourages a more flexible and decoupled design.

### **MVI (Model-View-Intent)** 📈
MVI is employed for managing the state and interactions within the application:
- **Model**: Represents the application's state and business logic.
- **View**: Displays the UI and reacts to state changes.
- **Intent**: Represents user actions or events that drive changes in the state.

By applying MVI, we achieve a unidirectional data flow, making state management predictable and consistent.

This architecture ensures that our application is well-structured, easy to maintain, and scalable, while adhering to best practices and design principles.

## App Screenshots 📸

Dive into **SaboryTV** and explore its vibrant and intuitive design with these screenshots showcasing the heart of our app!

### Onboarding 🏠

Kickstart your culinary adventure with our Onboarding screens. If you’re new to **SaboryTV** and haven’t logged in yet, you’ll be greeted by our welcoming landing page. Here, you can get a sneak peek of what’s in store 📜 and easily navigate to login 🔒 or create a new account 🆕 to start your flavorful journey.

<p align="center">
  <img src="doc/screenshots/picture_1.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_2.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_3.png" />
</p>

### Sign In 🔑

Ready to join the **SaboryTV** kitchen? Log in with your email and password to access all the delicious features awaiting you. If you’re not yet a member, don’t worry—our sign-up process is just a tap away! 🌟✉️

<p align="center">
  <img src="doc/screenshots/picture_4.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_5.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_6.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_7.png" />
</p>

### Sign Up ✨

Welcome to **SaboryTV**! 🎉 Setting up your account is a breeze and opens the door to a world of culinary delights. Ready to cook up something amazing? 🍽️

Simply enter your details: your name 📝, email 📧, and a secure password 🔒. Choose a password that’s memorable yet secure—your culinary adventure is our priority!

Once you’ve filled in the fields, hit "Register" ✅ and you’re all set! 🎊 You’re now part of the **SaboryTV** family, ready to explore and enjoy exclusive recipes and features. 🚀🌟

<p align="center">
  <img src="doc/screenshots/picture_19.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_20.png" />
</p>

### Managing Your Profiles 👤

Step into the **Profiles** section where customization and ease meet to enhance your cooking journey. Here’s how you can make **SaboryTV** truly yours:

- **Profile Selection**: Choose which profile you’d like to use from the **Profile Selection** screen. It’s your personal space where you can keep track of favorite recipes and tailored recommendations.

<p align="center">
  <img src="doc/screenshots/picture_8.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_9.png" />
</p>

- **Creating Profiles**: Add up to four profiles to cater to family members or different users. Customize each profile with its own alias and avatar, and make cooking a fun, personalized experience for everyone!

<p align="center">
  <img src="doc/screenshots/picture_14.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_15.png" />
</p>

- **Editing Profiles**: Update your profile’s alias and avatar or change your security PIN with ease. Keep your account secure and reflect your personality in every way you choose.

<p align="center">
  <img src="doc/screenshots/picture_10.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_11.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_12.png" />
</p>

- **Deleting Profiles**: If a profile is no longer needed, delete it and remove all associated favorites and data. This keeps your app clean and organized, focusing on the recipes and features that matter most to you.

<p align="center">
  <img src="doc/screenshots/picture_13.png" />
</p>

The **Profiles** section is all about flexibility and personalization, ensuring your cooking journey is as unique as you are.

<p align="center">
  <img src="doc/screenshots/picture_16.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_17.png" />
</p>

### Exploring the Home Screen 📱

Welcome to the vibrant **Home Screen**, the hub of your culinary adventure. Here’s what awaits you:

- **Featured Recipes Carousel** 🎠: Swipe through our rotating selection of standout recipes. This dynamic showcase highlights popular and trending dishes to inspire your next culinary creation.

<p align="center">
  <img src="doc/screenshots/picture_18.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_21.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_22.png" />
</p>

- **Categories Row** 📊: Browse through a variety of recipe categories, from appetizers to desserts. Presented in a sleek horizontal list, it’s easy to find recipes based on what you’re craving or your current cooking goals.

<p align="center">
  <img src="doc/screenshots/picture_23.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_25.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_26.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_27.png" />
</p>

- **Personalized Recipe Recommendations** ⭐: Discover recipes tailored to your taste and preferences. Based on your cooking history and likes, this section offers suggestions that are perfect for your culinary journey.

<p align="center">
  <img src="doc/screenshots/picture_24.png" />
</p>

The Home Screen is designed to make your exploration of recipes delightful and engaging. Whether you’re checking out featured dishes, exploring categories, or receiving personalized suggestions, everything is organized to enhance your cooking experience.

## Exploring Recipes and More 🍽️✨

Step into the **Recipes** section, your ultimate culinary playground where your cooking adventures come to life! 🎉 This hub is designed to help you explore and enjoy a variety of recipes that cater to your dietary preferences and goals.

<p align="center">
  <img src="doc/screenshots/picture_28.png" />
</p>

In this section, you’ll find a diverse array of categories and types to explore:

- **Recipe Types** 🌱🍲: Discover a wide range of recipes tailored to different dietary needs:
  - **Vegan** 🌿: Plant-based recipes that are completely free from animal products. Perfect for those who follow a vegan lifestyle or want to try something new and plant-powered.
  - **Vegetarian** 🥕: Delicious recipes that exclude meat but may include dairy and eggs. Ideal for those who prefer a vegetarian diet or are looking for tasty meat-free options.
  - **Gluten-Free** 🌾🚫: Mouthwatering dishes that are free from gluten, perfect for those with gluten sensitivities or celiac disease.
  - **High-Protein** 💪: Recipes packed with protein to support muscle building and recovery. Great for athletes or anyone looking to boost their protein intake.

<p align="center">
  <img src="doc/screenshots/picture_29.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_30.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_31.png" />
</p>

- **Categories** 📚: Browse through various recipe categories to find exactly what you’re in the mood for:
  - **Appetizers** 🍢: Tantalizing starters to kick off your meal with flair.
  - **Main Courses** 🍽️: Hearty and satisfying dishes that are the centerpiece of your meal.
  - **Side Dishes** 🥗: Delicious sides to complement your main course and add variety to your plate.
  - **Desserts** 🍰: Sweet treats and indulgent desserts to finish your meal on a high note.

<p align="center">
  <img src="doc/screenshots/picture_32.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_33.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_34.png" />
</p>

- **Special Features** ✨: Take advantage of our advanced filtering and sorting options 🔍. Customize your search based on preparation time, recipe type, or dietary restrictions to find the perfect recipe for any occasion.

Once you find a recipe that excites your taste buds, check out the **detailed view** 📋. Here you’ll get all the essential information, including ingredients, instructions, preparation time, and any special tips, so you can easily recreate it in your own kitchen.

The **Recipes** section is crafted to be your personal culinary hub, where every meal is an adventure waiting to happen. Whether you’re exploring new dietary options or looking for your next favorite dish, this section has everything you need to make your cooking experience enjoyable and rewarding. 🌟🚀

<p align="center">
  <img src="doc/screenshots/picture_35.png" />
</p>

### Favorites Section 🌟

Welcome to the **Favorites** section, your personal culinary treasure trove! Here you can keep all your cherished recipes just a tap away. 🍽️✨ This space is designed to make your cooking adventures even more enjoyable by providing quick access to your most-loved dishes.

<p align="center">
  <img src="doc/screenshots/picture_36.png" />
</p>

#### What’s in the Favorites Section?

- **Quick Access:** Instantly view all the recipes you’ve marked as favorites, so you can easily revisit and enjoy your top picks without searching again. ❤️
- **Detailed View:** Tap on any favorite recipe to dive straight into its detailed view. You’ll find all the essential details, from ingredients to step-by-step instructions, right at your fingertips. 📋
- **Easy Cook:** With just one click, you can start cooking any of your favorite recipes immediately. It’s all about making your kitchen experience as seamless and delightful as possible! 🚀

Make your cooking journey more efficient and personalized by keeping your favorite recipes close at hand. Enjoy effortless access to the dishes you love most, and savor every meal with ease! 🌟🍴

<p align="center">
  <img src="doc/screenshots/picture_37.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_38.png" />
</p>

### Recipe Player 🎥🍽️

The **Recipe Player** is where your culinary creations come to life on SaboryTV. Designed to provide a seamless and immersive cooking experience, it ensures you stay engaged and inspired throughout your culinary journey.

- **High-Quality Video** 📺: Experience your recipes in stunning detail with support for Full HD and even 4K resolutions. The player adapts automatically to offer the best quality based on your connection, so you never miss a step.

- **Recipe Controls** ⏯️: Effortlessly navigate through your cooking video with intuitive controls. You can pause, rewind, or skip forward, ensuring you stay right on track with your recipe.

- **On-Screen Guidance** 🧭: Follow along with clear, on-screen instructions that guide you through each cooking step. Real-time tips and tricks from expert chefs help you master every dish with confidence.

- **Progress Tracking** 📊: Keep track of your progress with visual cues that show how far you’ve come in the recipe and what’s left to do. Stay motivated as you cook through each step.

- **Full-Screen Mode** 🖥️: Dive into your recipe with full-screen mode, minimizing distractions and allowing you to focus completely on your cooking.

The **Recipe Player** on SaboryTV is your ultimate kitchen companion, ensuring every recipe is not only delicious but also fun to prepare. Whether you're whipping up a quick meal or crafting a gourmet feast, the player provides everything you need for an exceptional cooking experience. 🍴🌟

### Subscription Options 🌟🍴

In the **Subscriptions** section, discover flexible plans designed to enhance your culinary adventures on SaboryTV! 🎉✨ Whether you’re a casual cook or a culinary enthusiast, our subscription options offer exclusive access to premium recipes and features that will elevate your cooking experience.

<p align="center">
  <img src="doc/screenshots/picture_46.png" />
</p>

Here’s what you need to know:

- **Choose Your Plan** 🗓️: Pick the subscription that fits your cooking style and culinary goals. We offer three options: 1 month, 6 months, or 12 months. Each plan is crafted to provide continuous inspiration and access to a diverse array of recipes.

- **Enjoy Discounts** 💰: The longer you commit, the more you save! Opt for a longer subscription period and enjoy special discounts. It’s a win-win—more savings and more delicious recipes at your fingertips!

- **Unlock Premium Recipes** 🔓: With a subscription, you’ll gain exclusive access to high-quality, premium recipes not available in the free plan. Dive into gourmet dishes, advanced cooking techniques, and exclusive chef tutorials that will take your culinary skills to the next level.

The **Subscriptions** section ensures that your cooking journey is not only flavorful but also rewarding. With the flexibility to choose your plan and the added value of premium recipes, you’ll stay inspired and engaged, creating amazing dishes with ease. 🍽️🚀

<p align="center">
  <img src="doc/screenshots/picture_47.png" />
</p>

### Settings 🛠️🍽️

The **Settings** section on SaboryTV is your personal culinary control center, where you can fine-tune your app experience to match your tastes and preferences. 🌟 Here’s what you can customize:

<p align="center">
  <img src="doc/screenshots/picture_39.png" />
</p>

- **Adjust Language** 🌐: Choose the language that makes you feel most at home. Whether you prefer English, Spanish, or another language we offer, SaboryTV will adapt to your choice, ensuring a seamless and enjoyable cooking experience.

<p align="center">
  <img src="doc/screenshots/picture_41.png" />
</p>

- **Select Measurement Units** 📏: Pick your preferred unit of measurement—metric or imperial. Whether you’re accustomed to grams and liters or ounces and cups, SaboryTV will display recipe measurements in the format that suits you best.

<p align="center">
  <img src="doc/screenshots/picture_40.png" />
</p>

- **Set Video Quality** 📹: Control your video streaming experience by selecting the video quality. Choose from Full HD for crisp, clear visuals or opt for Automatic to let the app adjust the quality up to 4K based on your internet connection.

<p align="center">
  <img src="doc/screenshots/picture_42.png" />
</p>

- **Learn About Us** 🧑‍🍳: Discover more about the team behind SaboryTV and the vision that drives our platform. We’re excited to share our passion for food and cooking with you!

<p align="center">
  <img src="doc/screenshots/picture_43.png" />
</p>

- **Log Out** 🚪: Need to take a break or switch accounts? Easily log out and take a pause or switch profiles with just a few taps.

<p align="center">
  <img src="doc/screenshots/picture_44.png" />
</p>

<p align="center">
  <img src="doc/screenshots/picture_45.png" />
</p>

- **Manage Subscriptions** 💳: Review and adjust your subscription plan. Change your current plan or even cancel your subscription if needed. Stay in control of your culinary journey and ensure your plan meets your evolving tastes and needs.

The **Settings** section ensures that you have the flexibility to tailor SaboryTV to your liking, enhancing your overall cooking experience. From language preferences to video quality settings, everything is designed to offer you a personalized and delightful culinary adventure. 🍽️🛠️


## Contribution
Contributions to SaboryTV Android are highly encouraged! If you're interested in adding new features, resolving bugs, or enhancing the project's functionality, please feel free to submit pull requests.

## Credits
SaboryTV is developed and maintained by Sergio Sánchez Sánchez (Dream Software). Special thanks to the open-source community and the contributors who have made this project possible. If you have any questions, feedback, or suggestions, feel free to reach out at dreamsoftware92@gmail.com.

## Acknowledgements 🙏

We express our deep appreciation to [Freepik](https://www.freepik.es/) for generously providing the resources used in this project.
<div> Icons and images takes from <a href="https://www.freepik.com" title="Freepik"> Freepik </a> from <a href="https://www.flaticon.es/" title="Flaticon">www.flaticon.es'</a></div>


A heartfelt thank you to the creators of the [JetFit repository](https://github.com/TheChance101/tv-samples/tree/JetFit/JetFit) for providing such an invaluable starting point for Jetpack Compose for TV. Your work has been incredibly inspiring and instrumental in shaping the development of this project.

The resources, examples, and insights provided in the JetFit repository served as a foundational reference, enabling us to build and expand upon your excellent groundwork. Your contributions have significantly accelerated our journey and enriched our understanding of creating seamless TV applications using Jetpack Compose. 📺✨

<a href="https://www.freepik.es/foto-gratis/espaguetis-salsa-bolonesa-tablexa-madera_38744102.htm#fromView=search&page=1&position=2&uuid=d536ab4f-d11c-49d5-82d3-eb4f117e0df0">Imagen de chandlervid85 en Freepik</a> - 
<a href="https://www.freepik.es/foto-gratis/fettucine-salsa-crema-blanca-camarones-champinones_3519157.htm#fromView=search&page=1&position=2&uuid=ece10ef8-c656-4e0d-ad2d-46d519bb5465">Imagen de lifeforstock en Freepik</a> - 
<a href="https://www.freepik.es/foto-gratis/tacos-carne-mesa-madera_1138286.htm#fromView=search&page=1&position=6&uuid=97d9bfad-76c2-4739-af1a-95d93bb85e9a">Imagen de jcstudio en Freepik</a> - 
<a href="https://www.freepik.es/foto-gratis/ensalada-cesar-vista-superior-plato-ovalado-verde-mantel-cuadros-blanco-tenedor-cuchillo-sobre-fondo-rojo-oscuro_16936995.htm#fromView=search&page=1&position=1&uuid=0462af90-852d-4da4-833d-c6a9d3b2160d">Imagen de KamranAydinov en Freepik</a> - 
<a href="https://www.freepik.es/foto-gratis/salteado-pollo-pimientos-judias-verdes_7701182.htm#fromView=search&page=1&position=1&uuid=af4272c3-0220-4e72-bfb8-3c53f11c0c0b">Imagen de timolina en Freepik</a> - 
<a href="https://www.freepik.es/foto-gratis/sabrosos-apetitosos-camarones-fritos-sarten-sobre-fondo-piedra-oscura-vista-superior_21707372.htm#fromView=search&page=1&position=1&uuid=d99fcafa-3c6d-4c9f-b7ce-4d4435e102f8">Imagen de valeria_aksakova en Freepik</a> - 
<a href="https://www.freepik.es/foto-gratis/ensalada-griega-o-horiatiki-grandes-trozos-tomates-pepinos-cebolla-queso-feta-aceitunas-tazon-blanco-aislado-vista-superior-ensalada-pueblo-dados-mozzarella-rucula-perejil-aceite-oliva_14628711.htm#fromView=search&page=1&position=9&uuid=dffa7b02-a748-4fe0-a1c7-f808e48beadd">Imagen de jcomp en Freepik</a> - 
<a href="https://www.freepik.es/foto-gratis/curry-pollo-cebolla-comida-india-cocina-asiatica-vista-superior_6713076.htm#fromView=search&page=1&position=44&uuid=ba0274b8-7205-48e0-9970-ad0ff12d6c68">Imagen de timolina en Freepik</a> -
<a href="https://www.freepik.es/foto-gratis/sabrosos-sabrosos-aperitivos-italianos-tomate-o-bruschetta-sobre-rebanadas-baguette-tostado-aderezado-albahaca-verduras-hierbas-sobre-pan-ciabatta-crujiente-parrilla-o-tostado_14628415.htm#fromView=search&page=1&position=5&uuid=30263806-71e9-44b8-b2a6-cc1526db37f3">Imagen de jcomp en Freepik</a> - 
<a href="https://www.freepik.es/foto-gratis/champinones-rellenos-caseros-al-horno-eneldo-fresco-queso_8826550.htm#fromView=search&page=1&position=5&uuid=9334ae69-3355-41a3-9931-03ff1abe212d">Imagen de azerbaijan_stockers en Freepik</a> - 
<a href="https://www.freepik.es/foto-gratis/mojito-ingredientes_8749127.htm#fromView=search&page=1&position=5&uuid=bc645d5f-ad64-4840-9f41-359fa645c1ba">Imagen de Racool_studio en Freepik</a> - 
<a href="https://www.freepik.es/foto-gratis/bebida-verano-frambuesas-limon-hielo-mesa-madera-antigua_20989797.htm#fromView=search&page=1&position=1&uuid=b917e65a-b2ae-4423-b7cd-5a35e81cddaa">Imagen de fabrikasimf en Freepik</a> - 
<a href="https://www.freepik.es/foto-gratis/bebida-madera-detox-verano-sacudida_1130230.htm#fromView=search&page=1&position=1&uuid=4443741b-8e77-43c3-a6c9-6ab77192c58c">Imagen de topntp26 en Freepik</a> - 
<a href="https://www.freepik.es/foto-gratis/latte-helado-matcha_1154010.htm#fromView=search&page=1&position=10&uuid=43a34124-12c7-4bee-a054-051fb606dedc">Imagen de topntp26 en Freepik</a>
