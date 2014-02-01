My-YouTube--Web-App-on-Cloud
============================

In this assignment, you create your own version of a video streaming and sharing application and host it on the cloud. Further, you implement a rating system for your videos and display videos in order of these rating. 
 
Here's a few details about what your application should be like:
 
1. An upload button to upload new videos

2. A page that lists all the videos uploaded

3. Rating button (dropdown or anything else) for each 

4. Delete button to delete video
<br>

Functionalities:

Upload:

When the user chooses to upload a new video, it should be stored using an Amazon S3 bucket. 

Note, if a file other than compatible video files are uploaded, it should throw an error message.
You should also have an option to delete the videos. On deleting, the page should refresh and the videos should no longer show up.

List:

A page should list all available videos from the S3 bucket and updates them on the page. (Example: There are 4 videos on my page. If I upload a 5th video using the Upload button and click on the List button, the new video will be added to the page and there will be 5 videos on my webpage now.)

Stream:

The users should be able to watch the videos on the browser. You may use the sample code for JWPlayer to stream the video.

However, the videos stored in S3 must be distributed using CloudFront, which is the content distribution server. Note, you will have to create both distributions, Streaming (for Flash streaming) and Downloading (for HTML5 streaming). 


Rating:

You need to use the Amazon RDS database to store details about the video: name, upload timestamp and a cumulative rating (ie, they should be updated and averaged everytime a user rates it). These ratings should be updated everytime a new user rates it. Note, you can allow the same user to rate multiple times to avoid storing any session/cookies data. Current rating must also be displayed next to every video. The RDS can be created and configured manually but must be updated using code.
