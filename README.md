meta-rdk-ccsp
=============

All CCSP recipes are in these subdirectories.



To test this component against a standard core-image-minimal yocto build:

1) edit poky/build/conf/bblayers.conf and add the following to BBLAYERS
   /home/smaynard/yocto/ccsp/meta-rdk-ccsp \

2) edit poky/meta/recipes-core/packagegroups/packagegroup-core-boot.bb and add to RDEPENDS
   CcspCommonLibrary \

then bitbake core-image-minimal
