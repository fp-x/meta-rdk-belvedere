meta-rdk-ccsp
=============

All CCSP recipes are in these subdirectories.



To test this component against a standard core-image-minimal yocto build:

1) edit <i>poky/build/conf/bblayers.conf</i> and add the following to BBLAYERS<br><b>/home/smaynard/yocto/ccsp/meta-rdk-ccsp \\</b>

2) edit <i>poky/meta/recipes-core/packagegroups/packagegroup-core-boot.bb</i> and add the folowing to RDEPENDS<br>
   <b>CcspCommonLibrary \\</b>

3) then execute <i>bitbake core-image-minimal</i>
