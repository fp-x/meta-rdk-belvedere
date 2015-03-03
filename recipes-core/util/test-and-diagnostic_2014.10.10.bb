SUMMARY = "CCSP test and diagnostice utilities."
HOMEPAGE = "http://github.com/ccsp-yocto/TestAndDiagnostic"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

DEPENDS = "ccsp-common-library"

SRC_URI = "\
    git://github.com/ccsp-yocto/TestAndDiagnostic.git;protocol=git;branch=daisy;rev=daisy \
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
    install -D -p -m 777 ${D}/usr/bin/CcspTandDSsp ${D}/usr/ccsp/tad/CcspTandDSsp
}

PACKAGES += "${PN}-ccsp"

FILES_${PN}-ccsp = " \
    ${libdir}/libdiagnostic.so.* \
    ${libdir}/libdmltad.so.* \
    ${bindir}/CcspTandDSsp \
    ${prefix}/ccsp/tad/CcspTandDSsp \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/tad/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"