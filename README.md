meta-rdk-ccsp
=============

All CCSP recipes are in these subdirectories.

As recipes are written for components, the SRCREV should be made automatic based on each components branch "daisy"; i.e. each component's master branch will be buildable on the pc where as it's daisy branch will be buildable in yocto.

Depending on your Yocto installation of poky, you maye have to:<br>
cd poky
git pull
git clone -b daisy git://git.openembedded.org/meta-openembedded

To test this component against a standard core-image-minimal yocto build:

1) edit <i>poky/build/conf/bblayers.conf</i> and add the following to BBLAYERS<br>
   <b>/home/smaynard/poky/meta-openembedded/meta-networking \\</b><br>
   <b>/home/smaynard/yocto/ccsp/meta-rdk-ccsp \\</b>

2) edit <i>poky/meta/recipes-core/packagegroups/packagegroup-core-boot.bb</i> and add the folowing to RDEPENDS:<br>
   <b>CcspCommonLibrary \\</b><br>
   <b>hal \\<\b><br>
   <b>CcspCMAgent \\<\b><br>
   <b>CcspCr \\<\b><br>
   <b>CcspLMLite \\<\b><br>
   <b>CcspMisc \\<\b><br>
   <b>CcspMtaAgent \\<\b><br>
   <b>CcspPandM \\<\b><br>
   <b>CcspPsm \\<\b><br>
   <b>CcspSnmpPa \\<\b><br>
   <b>CcspTr069Pa \\<\b><br>
   <b>CcspWifiAgent \\<\b><br>
   <b>RebootManager \\<\b><br>
   <b>TestAndDiagnostic \\<\b><br>

3) then execute <i>bitbake core-image-minimal</i>
