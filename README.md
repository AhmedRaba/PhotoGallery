# 📸 Photo Gallery App

A simple yet robust Android application built for the Route Job Fair Android Task. The app showcases a list of photos using the [Pexels API](https://www.pexels.com/api/) or [JSONPlaceholder](https://jsonplaceholder.typicode.com/photos) with offline caching, network status tracking, light/dark mode toggle, and clean architecture implementation.

---

## 🚀 Features

- 🖼️ Fetch and display photos from an API
- ⚙️ Clean Architecture with MVVM
- 🔁 Offline support with Room Database
- 📶 Online/Offline network status indicator
- 🌙 Light and Dark mode support (toggle switch in top bar)
- 🌐 Retrofit + Gson for networking
- 🧠 Dependency Injection with Hilt
- 🧩 Glide for image loading and caching
- 🔄 Pagination for photo loading

---

## 📲 Screenshots

| Splash - Light Mode | Splash - Dark Mode |
|---------------------|--------------------|
| <img src="https://github.com/user-attachments/assets/3de4b7dc-f832-456b-92ce-9f6812815d2a" width="300"/> | <img src="https://github.com/user-attachments/assets/19a268ce-b38e-4980-bb74-b0c3f6959d98" width="300"/> |

| Online - Light Mode | Online - Dark Mode |
|---------------------|--------------------|
| <img src="https://github.com/user-attachments/assets/bd548c02-8db3-4df1-ac48-5fd8f40b31a6" width="300"/> | <img src="https://github.com/user-attachments/assets/871330cc-1bfd-4056-9705-c40c546080b6" width="300"/> |

| Offline (Cache) - Light Mode | Offline (Cache) - Dark Mode |
|-----------------------------|----------------------------|
| <img src="https://github.com/user-attachments/assets/bb30bbb3-94b8-40fb-b552-b0f31b7cbeae" width="300"/> | <img src="https://github.com/user-attachments/assets/2974074d-8b41-4167-8633-f4b9f176fadf" width="300"/> |

| Loading State | Error State |
|---------------|-------------|
| <img src="https://github.com/user-attachments/assets/4d8ddfe8-bcb9-4271-97c8-a4df42d9a988" width="300"/> | <img src="https://github.com/user-attachments/assets/7b5c9688-cc81-498d-865a-bb5c7b9e64d3" width="300"/> |

---


## 🧱 Architecture

The app follows **Clean Architecture** and the **MVVM** design pattern. Here's the layer-wise breakdown:

### 🔹 Data Layer
- **Retrofit API Service**: Fetch data from the API.
- **Room Database**: Cache data for offline use.
- **Repository**: Decides whether to load from API or DB.

### 🔹 Domain Layer
- **Models**: Kotlin data classes used across layers.
- **Use Cases**: Encapsulate business logic (e.g., fetch photos, check if cached, etc.)

### 🔹 Presentation Layer
- **ViewModel**: Connects UI and use cases using Kotlin Flow.
- **Composable Screens**: Display data using Jetpack Compose.
- **NetworkStatusTracker**: Tracks online/offline status using `ConnectivityManager`.

