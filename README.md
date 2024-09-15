# SaboryTV 🍳📺: Your Personal Chef, Right on Your TV

<img width="auto" height="200px" align="left" src="doc/main_logo.png" />

Welcome to **SaboryTV**, the ultimate platform to elevate your cooking experience directly from your Smart TV. Whether you're aiming to refine your culinary skills, explore new flavors, or simply follow along with top chefs, SaboryTV is designed to cater to your tastes and dietary goals. With an extensive collection of recipes, tailored to your preferences, you'll be able to create the perfect dish every time. 🍝🍰

With SaboryTV, you can watch expert chefs in action and follow their step-by-step instructions from the comfort of your kitchen. The platform offers an interactive, hands-free experience that guides you through every detail of the recipe—from ingredient prep to plating—so you can focus on perfecting your dish without distractions. 👨‍🍳👩‍🍳

**Built using Jetpack Compose for TV**, SaboryTV integrates seamlessly into your Smart TV environment. Utilizing [**🍮 Fudge**](https://github.com/sergio11/fudge_tv_compose_library), a powerful UI Kit for TV apps, SaboryTV ensures smooth navigation and a delightful user experience. Our platform is designed to make following along with cooking videos intuitive, engaging, and fun, turning your kitchen into a culinary playground. 🍽️✨

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

### Sign In 🔑

Ready to join the **SaboryTV** kitchen? Log in with your email and password to access all the delicious features awaiting you. If you’re not yet a member, don’t worry—our sign-up process is just a tap away! 🌟✉️

### Sign Up ✨

Welcome to **SaboryTV**! 🎉 Setting up your account is a breeze and opens the door to a world of culinary delights. Ready to cook up something amazing? 🍽️

Simply enter your details: your name 📝, email 📧, and a secure password 🔒. Choose a password that’s memorable yet secure—your culinary adventure is our priority!

Once you’ve filled in the fields, hit "Register" ✅ and you’re all set! 🎊 You’re now part of the **SaboryTV** family, ready to explore and enjoy exclusive recipes and features. 🚀🌟

### Managing Your Profiles 👤

Step into the **Profiles** section where customization and ease meet to enhance your cooking journey. Here’s how you can make **SaboryTV** truly yours:

- **Profile Selection**: Choose which profile you’d like to use from the **Profile Selection** screen. It’s your personal space where you can keep track of favorite recipes and tailored recommendations.

- **Creating Profiles**: Add up to four profiles to cater to family members or different users. Customize each profile with its own alias and avatar, and make cooking a fun, personalized experience for everyone!

- **Editing Profiles**: Update your profile’s alias and avatar or change your security PIN with ease. Keep your account secure and reflect your personality in every way you choose.

- **Deleting Profiles**: If a profile is no longer needed, delete it and remove all associated favorites and data. This keeps your app clean and organized, focusing on the recipes and features that matter most to you.

The **Profiles** section is all about flexibility and personalization, ensuring your cooking journey is as unique as you are.

### Exploring the Home Screen 📱

Welcome to the vibrant **Home Screen**, the hub of your culinary adventure. Here’s what awaits you:

- **Featured Recipes Carousel** 🎠: Swipe through our rotating selection of standout recipes. This dynamic showcase highlights popular and trending dishes to inspire your next culinary creation.

- **Categories Row** 📊: Browse through a variety of recipe categories, from appetizers to desserts. Presented in a sleek horizontal list, it’s easy to find recipes based on what you’re craving or your current cooking goals.

- **Personalized Recipe Recommendations** ⭐: Discover recipes tailored to your taste and preferences. Based on your cooking history and likes, this section offers suggestions that are perfect for your culinary journey.

The Home Screen is designed to make your exploration of recipes delightful and engaging. Whether you’re checking out featured dishes, exploring categories, or receiving personalized suggestions, everything is organized to enhance your cooking experience.


## Contributing 🤝

We welcome contributions to improve SaboryTV. If you have suggestions or find bugs, please open an issue on our GitHub repository. Pull requests are also welcome! 💡🔧

## License 📜

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

Happy cooking with SaboryTV! 🎉👨‍🍳
