# Just playing around with Android Things, Kotlin, Google Assistant, and lego Trains

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
their Android projects, questions, and motivation. Our Grow With Google Nanodegrees Slack team already had a 
"student_leaders" channel, but on the morning of May 23 an "and_track_leaders" channel was created as well, and when I 
saw it and that I had been added to it, what popped into my head was that for a fun project for playing with both my 
Android Things kit and Kotlin I should write an app and wire the Android Things kit up to display a scrolling "GWG AND 
TRACK" marquee message and scrolling rainbow LED strip on the 
[Rainbow HAT](https://shop.pimoroni.com/products/rainbow-hat-for-android-things), while mounted to and powering (via a 
[motor](https://www.sparkfun.com/products/11696) from a 
[Sparkfun RedBoard (Arduino clone) kit](https://www.sparkfun.com/products/retired/11576) I got at the [Silicon Chef
hardware hackathon](https://hackbrightacademy.com/blog/hackbright-academys-silicon-chef-hosts-200-makers-sold-women-centric-hackathon/)) 
a Brio train in circles around an actual track.

So right now with this app and an assembled Android Things Starter Kit, when you press button A on the Rainbow HAT it 
toggles the scrolling displays on and off, and when you press button B it toggles the motor on and off via pin 
`GPIO2_IO01` on `JP2` on the NXP Pico i.MX7D board, and a little breadboard circuit. But when I was experimenting with 
powering a Brio train using a rubberband between the motor and one of the train's axles, this did not work well, plus 
it's been long enough since we'd gotten the Brio trains out that they were smaller than I remembered -- not very well 
suited to carrying the electronics. 

On to plan B (for **B**etter :-)): To dig through the garage until I find where the box of old German Lego trains and 
tracks wound up, and hook everything up to one of those instead.

Unfortunately, (1) digging has not yet produced the trains, and (2) on June 13 I had emergency surgery to repair a
partly-detached retina, and afterward I was not only not supposed to do any heavy lifting, but also supposed to keep my 
head/eyes facing down all the time to help the retina reattach, so no more digging in the garage for a while. :-(

Then yesterday in my facing-down state I watched (on my tablet in my lap) a Systers <TechTalks/> 
"[Getting Started with Actions on Google Workshop](https://www.cvent.com/c/express/603ae3c6-e101-4a0d-81b4-c8d66cd68a35)", 
and decided that despite the trains still being AWOL, I would revisit the project anyway to add some voice control in 
addition to the button control. So that's what I'll do next.

Anyway, more later! I'll add a photo, video, and circuit diagram to this README once I finally get an actual train and 
track situation together.
