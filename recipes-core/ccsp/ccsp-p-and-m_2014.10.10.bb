SUMMARY = "CCSP PandMSsp component"
HOMEPAGE = "http://github.com/ccsp-yocto/CcspPandM"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

DEPENDS = "ccsp-common-library"

SRC_URI = "\
git://github.com/ccsp-yocto/CcspPandM.git;protocol=git;branch=daisy;rev=daisy \
    "

SRC_URI[md5sum] = "d338d61e396d5038025339bf5bdb169d"
SRC_URI[sha256sum] = "e6f5a166c0e0f775dc09261f992abb561b781f4a992ef2c0081edcf6b265df24"

S = "${WORKDIR}/git"

inherit autotools

CFLAGS_append = " \
    -I=${includedir}/dbus-1.0 \
    -I=${libdir}/dbus-1.0/include \
    -I=${includedir}/ccsp \
    "

LDFLAGS_append = " \
    -ldbus-1 \
    "

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp/pam
    install -m 777 ${D}/usr/bin/CcspPandMSsp -t ${D}/usr/ccsp/pam
    install -m 644 ${WORKDIR}/git/config/CcspPam.cfg -t ${D}/usr/ccsp/pam
    install -m 644 ${WORKDIR}/git/config/COSAXcalibur.XML -t ${D}/usr/ccsp/pam
    install -m 644 ${WORKDIR}/git/config/TR181-USGv2.XML -t ${D}/usr/ccsp/pam
}

do_install_append_qemux86 () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/CcspDmLib_pc.cfg ${D}/usr/ccsp/pam/CcspDmLib.cfg 
}

do_install_append_qemuarm () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/CcspDmLib_arm.cfg ${D}/usr/ccsp/pam/CcspDmLib.cfg 
}

do_install_append_raspberrypi () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/CcspDmLib_arm.cfg ${D}/usr/ccsp/pam/CcspDmLib.cfg 
}

PACKAGES += "${PN}-ccsp"

FILES_${PN}-ccsp = " \
    ${prefix}/ccsp/pam/CcspPandMSsp \
    ${prefix}/ccsp/pam/CcspDmLib.cfg \
    ${prefix}/ccsp/pam/CcspPam.cfg \
    ${prefix}/ccsp/pam/COSAXcalibur.XML \
    ${prefix}/ccsp/pam/TR181-USGv2.XML  \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/pam/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
