# LokalApp

LokalApp is an Android application that allows users to browse job listings and bookmark their favorite jobs. The app fetches job data from a remote API and stores it locally using Room database. Users can view job details and add jobs to their bookmarks, which can be accessed and viewed later.

## Features

- Browse job listings
- View job details
- Bookmark jobs
- View bookmarked jobs

## Architecture

The app follows the MVVM (Model-View-ViewModel) architecture pattern, utilizing Android's Jetpack components for a robust and maintainable codebase. 

## Libraries Used

- [Retrofit](https://square.github.io/retrofit/) - For network calls
- [Room](https://developer.android.com/jetpack/androidx/releases/room) - For local database
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - For reactive data handling
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - For managing UI-related data
- [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview) - For displaying lists of data
- [Coroutines](https://developer.android.com/kotlin/coroutines) - For asynchronous programming

## Getting Started

### Prerequisites

- Android Studio 4.0 or higher
- Android SDK 21 or higher
- Kotlin 1.4 or higher

### Installation

1. **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/lokalapp.git
    cd lokalapp
    ```

2. **Open the project in Android Studio:**
    - Open Android Studio.
    - Click on `File` -> `Open` and navigate to the project directory.

3. **Build the project:**
    - Click on `Build` -> `Make Project` or use the shortcut `Ctrl + F9`.

4. **Run the project:**
    - Connect your Android device or start an emulator.
    - Click on `Run` -> `Run 'app'` or use the shortcut `Shift + F10`.



## Usage

### Viewing Job Listings

- Launch the app to see a list of job listings fetched from the remote API.
- Click on a job item to view its details.

### Bookmarking Jobs

- In the job detail screen, click on the `Bookmark` button to save the job to your bookmarks.
- Access your bookmarks from the bookmarks section in the app.



## Acknowledgements

- [Retrofit](https://square.github.io/retrofit/)
- [Room](https://developer.android.com/jetpack/androidx/releases/room)
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview)
- [Coroutines](https://developer.android.com/kotlin/coroutines)
