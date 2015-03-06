SUMMARY = "CCSP CcspLMLite component"
HOMEPAGE = "http://github.com/ccsp-yocto/CcspLMLite"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

DEPENDS = "ccsp-common-library"

SRC_URI = "\
    git://github.com/ccsp-yocto/CcspLMLite.git;protocol=git;branch=150123-merge \
    "

SRCREV = "${AUTOREV}"
PV = "${RDK_RELEASE}+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools

CFLAGS_append = " \
    -I=${includedir}/dbus-1.0 \
    -I=${libdir}/dbus-1.0/include \
    -I=${includedir}/ccsp \
    "

#force lib to be built first
do_compile () {
    oe_runmake liblmapi.la
    oe_runmake all
}

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp/lm
    install -m 777 ${D}/usr/bin/CcspLMLite -t ${D}/usr/ccsp/lm
}

PACKAGES += "${PN}-ccsp"

FILES_${PN}-ccsp = " \
    ${prefix}/ccsp/lm/CcspLMLite \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/lm/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
