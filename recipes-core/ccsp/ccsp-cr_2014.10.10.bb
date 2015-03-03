SUMMARY = "CCSP CcspCrSsp component"
HOMEPAGE = "http://github.com/ccsp-yocto/CcspCr"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library"

SRC_URI = "\
git://github.com/ccsp-yocto/CcspCr.git;protocol=git;branch=daisy;rev=daisy \
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

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp
    install -m 777 ${D}/usr/bin/CcspCrSsp -t ${D}/usr/ccsp
}

do_install_append_qemux86 () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/cr-deviceprofile_pc.xml ${D}/usr/ccsp/cr-deviceprofile.xml
}

do_install_append_qemuarm () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/cr-deviceprofile_arm.xml ${D}/usr/ccsp/cr-deviceprofile.xml
}

do_install_append_raspberrypi () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/cr-deviceprofile_arm.xml ${D}/usr/ccsp/cr-deviceprofile.xml
}

PACKAGES += "${PN}-ccsp"

FILES_${PN}-ccsp = " \
    ${prefix}/ccsp/CcspCrSsp \
    ${prefix}/ccsp/cr-deviceprofile.xml \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
