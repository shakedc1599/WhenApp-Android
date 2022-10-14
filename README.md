# WhenApp Android
We have created an app "inspired" by WhatsApp for multi platform communication.

## Preview
<img src="https://user-images.githubusercontent.com/47411973/174489826-a74a391e-1f76-49de-a27a-4a14277b2d0b.png" width="300">
<img src="https://user-images.githubusercontent.com/47411973/174489926-fee402ab-90dd-40ec-9e0e-8ae92552d034.png" width="300">
<img src="https://user-images.githubusercontent.com/47411973/174489943-e99523a0-bd9e-406b-af0f-893bda131ccc.png" width="300">
<img src="https://user-images.githubusercontent.com/47411973/174489955-69e3a80f-14e1-4449-9f19-f2124378fd45.png" width="600">

## Development
The android app was created using Android studio, and the server side using ASP.NET.
Also used signalR, Room, Retrofit and Firebase libraries. 

## Features
1. Supports registration and login.
2. Great design.
3. Responsive server.
4. Landscape support.
5. Light and Dark modes.
6. support in multiple users
7. The data is saved both local and on the side of a server.

## Creators
this site was created by noam cohen, shaked cohen and roi avraham.

## How To Run

for this project you need to clone 3 repos:
1. [android app] (https://github.com/NoamCohen48/WhenAppAndroid2)
2. [Server (ASP WebAPI)] (https://github.com/Roi-Avraham/whenAppServer)

for running the android client:

run the server first and then run the app.

### for running the server:
1. clone repo
2. make sure to install mariadb with username root and password toor.
3. delete db called WhenUpDB if exist.

> you can skip those 2 steps by changing the db settings in `WhenAppContext`
> ![Screenshot_1](https://user-images.githubusercontent.com/92931230/169861142-caac3fb0-8244-4c7c-a4b3-6d7413e2cf57.png)

4. delete migration folder.
5. in package manager run `Add-Migration init` and `Update-Database`.
6. run the server

> if needed, install EntityFrameworkCore by entering int Package Manager Console  
> `Install-Package Pomelo.EntityFrameworkCore.MySql -Version 6.0.1`  
> `Install-Package Microsoft.EntityFrameworkcore.Tools -Version 6.0.1`  


### for running the android app
1. clone repo
2. clean project and build
> if dependencies error occures, change a dependency version in `app/build.gradle` and build (it will make it resync)  
> ![Screenshot_2](https://user-images.githubusercontent.com/92931230/176941157-54042a1f-1493-4071-94b7-0a0a178f2d7e.png)
3. run the app on the emulator

* default server is set to 10.0.2.2:5270

## Dependencies
this project uses:
- Android Native
  - Room
  - Retrofit
- ASP.net
- Firebase
