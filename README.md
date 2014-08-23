meta-rdk-ccsp
=============

All CCSP recipes are in these subdirectories.

As recipes are written for components, the SRCREV should be made automatic based on each components branch "daisy"; i.e. each component's master branch will be buildable on the pc where as it's daisy branch will be buildable in yocto.

Depending on your Yocto installation of poky, you maye have to:<br>
<b><i>cd poky</i></b><br>
<b><i>git pull</i></b><br>
<b><i>git clone -b daisy git://git.openembedded.org/meta-openembedded</i></b><br>

To test this component against a standard core-image-minimal yocto build:

1) edit <i>poky/build/conf/bblayers.conf</i> and add the following to BBLAYERS<br>
   <b>/home/smaynard/poky/meta-openembedded/meta-networking \\</b><br>
   <b>/home/smaynard/yocto/ccsp/meta-rdk-ccsp \\</b>

2a) To build for x86:<br>
<b><i>cd ~/poky</i></b><br>
<b><i>source oe-init-build-env</i></b><br>
<b><i>bitbake ccsp-test-image</i></b><br>
<b><i>runqemu qemux86</i></b><br>

2b) To Build for the qemuarm:<br>
<b><i>cd ~/poky</i></b><br>
<b><i>MACHINE=qemuarm source oe-init-build-env</i></b><br>
<b><i>MACHINE=qemuarm bitbake ccsp-test-image</i></b><br>
<b><i>runqemu qemuarm</i></b><br>

2c) To Build for the Raspberry PI:<br>
<b><i>cd ~/poky</i></b><br>
<b><i>git clone git://git.yoctoproject.org/meta-raspberrypi</i></b><br>
<b><i>MACHINE=raspberrypi source oe-init-build-env</i></b><br>
<b><i>edit conf/bblayers.conf and add ~/poky/meta-raspberrypi to the BBLAYERS</i></b><br>
<b><i>MACHINE=raspberrypi bitbake ccsp-test-image</i></b><br>
<b><i>dd resultant image to Raspberry Pi SD-Card</i></b><br>
