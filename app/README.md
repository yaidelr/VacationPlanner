# Vacation Management App

## Overview

The **Vacation App** is an Android mobile application designed to help users manage vacations and excursions. The app allows users to create, update, and delete vacations and excursions, view detailed information for each vacation, and set alarms for important vacation and excursion dates. The app is built using Java, compatible with Android 8.0 and higher, and utilizes the Room Framework for local database management.

## Purpose of the Application

The main purpose of the **Vacation Management App** is to allow users to easily manage their vacations and related excursions. Users can:
- Track their vacations by adding information such as title, hotel, start date, and end date.
- Add excursions to each vacation, track their dates, and set alarms for them.
- Ensure vacation and excursion dates are valid and within the specified timeframes.

The app includes detailed views for vacations and excursions, date validation, alerts, and sharing features, providing users with a comprehensive vacation management solution.

## How to Operate the Application

1. **Adding, Editing, and Deleting Vacations:**
   - Navigate to the **Add Vacation** screen.
   - Input details such as vacation title, hotel, start date, and end date.
   - Save the vacation, which will then be listed on the home screen.
   - To edit a vacation, select it from the list, make the changes, and save.
   - To delete a vacation, click the delete button (note: excursions associated with the vacation must be deleted first).
   - The app will validate that the end date is after the start date and ensure proper date formatting.

2. **Viewing Vacation Details:**
   - Select any vacation from the list to see its detailed view. Here, you can also edit or delete the vacation.
   - All associated excursions will be displayed on this screen.

3. **Adding, Editing, and Deleting Excursions:**
   - Select a vacation and navigate to **Add Excursion**.
   - Enter the excursion title and date, ensuring the date falls within the vacation's start and end dates.
   - Save the excursion. You can add as many excursions as you like.
   - To edit or delete an excursion, select it from the list and make the necessary changes.

4. **Setting Alarms for Vacations and Excursions:**
   - When viewing vacation or excursion details, you can set alarms for important dates.
   - For vacations, alarms can be set for the start and end dates.
   - For excursions, alarms can be set for the excursion date. Alarms will notify the user on the respective date.

5. **Sharing Vacation Details:**
   - In the detailed vacation view, you can share the vacation details via email, SMS, or clipboard. All vacation information will be pre-populated in the message.

6. **Validation and Alerts:**
   - Date validation is included for all vacations and excursions, ensuring that input dates are formatted correctly.
   - Vacation dates will also be validated to ensure that the end date occurs after the start date.
   - Excursion dates are validated to ensure they fall within the specified vacation timeframe.
   - Alerts will notify users about vacations starting or ending, and excursions occurring.

### Rubric Aspects Covered:

- **Page Layouts & Navigation:** The app contains a home screen, a list of vacations, detailed vacation and excursion views, and proper navigation between screens.
- **User Interface:** Designed with intuitive and user-friendly layouts for managing vacations and excursions.
- **Database-Backed Operations:** The app uses the Room Framework to handle database operations, storing and retrieving vacations and excursions consistently.
- **Date Validation & Alarms:** Validates vacation and excursion dates, and allows the user to set alarms for key dates.
- **Sharing Features:** Users can share vacation details via email, SMS, or clipboard.
- **CRUD Operations:** Supports full create, read, update, and delete operations for vacations and excursions.
- **Challenges & Problem Solving:** Date validation, alarm setting, and database management are the key challenges solved in this app.
- **Storyboard & Design:** Storyboards demonstrate the app’s flow, with screenshots showing layouts and features.

## Android Version Information

The signed APK is deployed for Android version **8.0 (Oreo)** and above. The app is compatible with devices running Android 8.0 or later.

## Git Repository

You can find the source code for this project on GitLab:

[Git Repository Link](https://gitlab.com/wgu-gitlab-environment/student-repos/yrondo1/d308-mobile-application-development-android.git)

## Deployment

- The APK can be downloaded from the [Releases](https://gitlab.com/wgu-gitlab-environment/student-repos/yrondo1/d308-mobile-application-development-android.git) section of the GitLab repository.
- The app is currently deployed for testing purposes and can be installed on any Android device running Android 8.0 (Oreo) or higher.


