meta-rdk-ccsp
=============

All CCSP recipes are in these subdirectories.

As recipes are written for components, the SRCREV should be made automatic based on each components branch "daisy"; i.e. each component's master branch will be buildable on the pc where as it's daisy branch will be buildable in yocto.


To test this component against a standard core-image-minimal yocto build:

1) edit <i>poky/build/conf/bblayers.conf</i> and add the following to BBLAYERS<br><b>/home/smaynard/yocto/ccsp/meta-rdk-ccsp \\</b>

2) edit <i>poky/meta/recipes-core/packagegroups/packagegroup-core-boot.bb</i> and add the folowing to RDEPENDS<br>
   <b>CcspCommonLibrary \\</b>

3) then execute <i>bitbake core-image-minimal</i>
