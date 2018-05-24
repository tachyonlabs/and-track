# Playing around with Android Things, Kotlin, and Brio Trains

## About the app/project

At Google I/O 2018 I got one of the 
[Android Things Starter Kits](https://androidthings.withgoogle.com/#!/kits/starter-kit) they were giving away as part 
of a scavenger hunt. Also, at all the Android sessions it was Kotlin Kotlin Kotlin on all the coding
screenshots and live demos -- I seriously saw more Python code projected on screens than I did Java this year.

Back in January I was one of the recipients of the 2018 "Grow with Google" Google Developer Challenge Scholarship, 
admitting me to a three-month "Grow with Google" Intermediate Android Developer challenge course exclusively for 
scholarship recipients, and then in April I received a scholarship to a special six-month Udacity/Google Android 
Developer Nanodegree program for the top 10% of students from the challenge course. 

The scholarship program actually covers four different specializations, so we were sorted into four houses (ABND, AND, 
FEND, and MWS), with each of us having gotten into phase 1 in the first place by receiving a "We are excited ..." 
letter offering us a place here, like the "We are pleased ..." Hogwarts letters, so as you can imagine I've been having 
good witchly/wizardly fun. I'm in house AND, presumably as in "Embrace the power of ..." in addition to Android 
Nanodegree.

I was also selected as a Student Leader for the program, and have been having a blast helping other students with 
their Android projects, questions, and motivation. Our Grow With Google Nanodegrees Slack team already has a 
"student_leaders" channel, but this morning an "and_track_leaders" channel was created as well, and when I saw it and 
that I had been added to it, what popped into my head was that for a fun project for playing with both my Android 
Things kit and Kotlin I should write an app and wire the Android Things kit up to display a scrolling "GWG AND TRACK" 
marquee message and scrolling rainbow LED strip on the 
[Rainbow HAT](https://shop.pimoroni.com/products/rainbow-hat-for-android-things), while mounted to and powering (via a 
[motor](https://www.sparkfun.com/products/11696) from a 
[Sparkfun RedBoard (Arduino clone) kit](https://www.sparkfun.com/products/retired/11576) I got at a hackathon) a 
Brio train in circles around an actual track.

So right now with this app and an assembled Android Things Starter Kit, when you press button A on the Rainbow HAT it 
toggles the scrolling displays on and off, and when you press button B it toggles the motor on and off via pin 
`GPIO2_IO01` on `JP2` on the NXP i.MX7D board, and a little breadboard circuit. But when I was experimenting with 
powering a Brio train using a rubberband between the motor and one of the train's axles, this did not work well, plus 
it's been long enough since we'd gotten the Brio trains out that they were smaller than I remembered -- not very well 
suited to carrying the electronics. Hmmm ... I think I will have to dig through the garage to figure out where the Lego 
trains wound up, and work on hooking everything up to one of those instead.

So ... more later! I'll add a photo, video, and circuit diagram to this README once I get an actual train and track 
situation together.
