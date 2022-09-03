## Android-APP-Football-Community (2021.9-2021.12)
This APP is designed for people who like playing football in the community. The purpose of Football Community APP is to create a strong atmosphere like professional football clubs for football lovers in the community and let them enjoy football more. Football lovers in the community can use this APP to join the team, create the team and transfer to another team. In their own team, they can see the team’s game/train announcement and starting lineup through the tactical board before a match, which is updated by the caption in the team. There is also a group chat function, which allows the team to discuss anything about football. If you are the captain of a team, that app will help you better manage the team, for instance, you can set the time and place of training games, use the tactical board function to show the team’s formation for the team, and introduce new players according to the positions that need to be strengthened. In addition, you can punch in and share your feelings about the game with the stadium’s location through map and location functions.


### 1. Configuration
`Development tool`: Android Studio 2021.2.1.16-windows


`Mobile model when develop`: Pixel XL API 29


### 2. Overall project description
The figure below shows the overall diagram for this project:
<div align=center><img src="https://github.com/WangHewei16/Football-Community-Android-APP/blob/main/images/Functions%20digrams.png" width="700"/></div>


The figure below shows the structure of this project. Three arrows with different colors (purple, yellow and blue) divided my
project into three parts: login relevant, team relevant and community circle relevant. In the process of testing, I first
conduct internal testing in three parts, and then conduct overall testing to ensure that there are almost no bugs in my
project.
<div align=center><img src="https://github.com/WangHewei16/Football-Community-Android-APP/blob/main/images/Interfaces%20display.png" width="1000"/></div>

### 3. Novel functions
`Football tactical board`: Users can drag and drop to move the position of each jersey number ImageView to achieve
the effect of arranging tactics and lineup personnel.


`Drawing board`: Users can click the pen button to mark the tactical board with a yellow pen, or click the eraser
button to erase the pen mark with an eraser. This function is to better show tactics for team members.


`Switch among tactical board, yellow pen and eraser`: There are three buttons in the lower left corner. User
can press each button to switch functions freely, which is a convenient design but the algorithm behind it is very
complex, and I will explain it in the video. Enter the “My Team” interface, the user enters the tactical board function
by default. Users can click the pen button to mark the tactical board with a yellow pen, or click the eraser button
to erase the pen mark with an eraser. The user can switch back to the tactical board to move the player by clicking
the button with the “turn back arrow”. The figure below shows the switch among these three functions.
<div align=center><img src="https://github.com/WangHewei16/Football-Community-Android-APP/blob/main/images/Tactical%26drawing%20board%20display.png" width="600"/></div>

### 4. Permission design
I design different permissions between team captain and member in “My Team” interface. Because football community need to have two identities: `team captain` and `team member`, to meet the actual condition, different permissions and functions for captains and members have been divided from this stage. In the my team interface,judge whether he is the team captain by querying the user data. Different identities have different functions and permissions after entering the “My Team” interface. The figure below shows the difference between two identities and the algorithm to achieve it.
<div align=center><img src="https://github.com/WangHewei16/Football-Community-Android-APP/blob/main/images/different%20permissions%20for%20captain%26member.png" width="550"/></div>

### 5. Test 
The table below shows the test result after two round black-box software testing.
<div align=center><img src="https://github.com/WangHewei16/Football-Community-Android-APP/blob/main/images/test%20condition.png" width="750"/></div>
