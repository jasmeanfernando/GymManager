# :weight_lifting_woman: GymManager
CS213: Projects 1-3

A fitness chain has gyms at 5 different locations in the central New Jersey area:
  - Bridgewater, 08807, Somerset County
  - Edison, 08837, Middlesex County
  - Franklin, 08873, Somerset County
  - Piscataway, 08854, Middlesex County
  - Somerville, 08876, Somerset County

GymManager is a simple software that helps the fitness chain manage the gym memberships and fitness class schedules.

The fitness chain provides fitness classes at each of their gyms and three membership options–standard, family, and premium. The software shall run the membership fee dues for all the members. Standard members can only check into fitness classes offered at the location where they are registered. Family and Premium members can check into any fitness class at any location, regardless of registered location. Family members also have 1 guest pass and Premium members have 3 guest passes. Guests (like standard members) can only check into fitness classes offered at the location where the member whose guest pass is being used is registered.

# :jigsaw: Setup Instructions

## Download JavaFX:
1) Dowload JavaFX: https://gluonhq.com/products/javafx/
2) Match appropriate operating system and architecture; Type should be SDK.
3) Downloaded product will be a zipped file.
4) Open zipped file and save file in the appropriate directory: /Users/jasmeanfernando/DevelopmentLibraries/javafx-sdk-19

## Download SceneBuilder:
1) Dowload SceneBuilder: https://gluonhq.com/products/scene-builder/

## Setup in Eclipse IDE:
1) Search **Eclipse Marketplace**.
  - Type in **fx**.
  - Install **e(fx) eclipse 3.8.0**
  - Restart Eclipse.
2) Search **Preferences**.
  - Select **JavaFX**.
  - Link where SceneBuilder is downloaded in **SceneBuilder executable**: /Applications/SceneBuilder.app
  - Click **Apply and Close**.
3) Search **Preferences**.
  - Select **Java** -> **Build Path** -> **User Libraries** -> **New** -> User library name: *JavaFX-19*
  - *JavaFX-19* -> **Add External JARs** -> /Users/jasmeanfernando/DevelopmentLibraries/javafx-sdk-19/lib -> Select all .jar files.
  - Click **Apply and Close**.
3) Create new JavaFX project.
  - **File** -> **New** -> **Project...** -> **JavaFX** -> **JavaFX Project** -> Project name: *GymManager*
4) Setting up libraries in *GymManager*:
  - Right-click on *GymManager* -> Select **Build Path** -> **Configure Build Path**
  - Click on **Libraries** tab -> Select **Classpath** -> **Add Library** -> **User Library** -> *JavaFX-19*
5) Setting up arguments in *GymManager*:
  - Delete *module-info.java*.
  - Rename *Main.java* to *GymManagerMain.java*.
  - **Run** -> **Run Configurations...** -> Select *GymManagerMain* -> Click on **Arguments** tab.
  - Paste the following:
    --module-path /path/to/javafx-sdk-19/lib (Refer to #4 in "Download JavaFX")
    --add-modules javafx.controls,javafx.fxml
  - Uncheck "Use the XstartOnFirstThreadArgument..."
6) Create .fxml file in *GymManager*:
  - **File** -> **New** -> **Other...** -> **New FXML Document** -> Name: *GymManagerView*
7) Create Controller file in *GymManager*:
  - Right-click on *GymManagerView.fxml* -> **Source** -> **Generate Controller** -> Name: *GymManagerController*

# :joystick: GymManager Functions

## Home
Load existing members from "MemberList.txt" file.
Load schedule from "classSchedule.txt" file.

<p align="center">
<img width="300" height="300" src="https://github.com/jasmeanfernando/GymManager/assets/98361155/febb15db-fb8d-40b7-b5a1-868c3b102f3a" alt="HomePage" title="HomePage">
<img width="300" height="300" src="https://github.com/jasmeanfernando/GymManager/assets/98361155/816b6051-4cbc-431e-8c79-f4197925fb7e" alt="HomePage" title="HomePage">
</p>

## Member Database
Members cannot be added to the database if they are: Not over the age of 18, have a future birth date, is a pre-existing member, or have an invalid gym location not run by the fitness chain. All new members will have a specific expiration date (Standard and family members expire in 3 months and premium members expire in 1 year). Members cannot be removed from the database if they do not exist.

<p align="center">
<img width="300" height="300" src="https://github.com/jasmeanfernando/GymManager/assets/98361155/24533bb9-8306-44cd-97b6-471768fa515d" alt="HomePage" title="HomePage">
<img width="300" height="300" src="https://github.com/jasmeanfernando/GymManager/assets/98361155/a8aee663-df2b-45f9-8d27-d6944ee594b6" alt="HomePage" title="HomePage">
</p>

## Member Check-In
Standards members are limited to a specific gym location. Members cannot check into a fitness class if:  Their membership has expired, they're not registered in the database, have a time conflict with a class they already added, and if they already checked into a class. Members cannot drop a fitness class if they never added it.

<p align="center">
<img width="300" height="300" src="https://github.com/jasmeanfernando/GymManager/assets/98361155/4ca2b567-dea7-49e2-b093-818eebc21483" alt="HomePage" title="HomePage">
<img width="300" height="300" src="https://github.com/jasmeanfernando/GymManager/assets/98361155/346957ca-fa25-4a83-a467-7a95487588c6" alt="HomePage" title="HomePage">
</p>

## Guest Check-In
Guests are limited to a specific gym location. Family members only have 1 guest pass and Premium members only have 3 guest passes (cannot use more). Everytime a guest checks out of a class, it returns the pass.

