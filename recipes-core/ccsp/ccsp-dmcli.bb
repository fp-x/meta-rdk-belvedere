SUMMARY = "CCSP Command Line Interface."
HOMEPAGE = "http://github.com/belvedere-yocto/CcspDmCli"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library"

SRC_URI = "\
    git://github.com/belvedere-yocto/CcspDmCli.git;protocol=git;branch=dbus_refactor_daisy \
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

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp
    install -m 777 ${D}/usr/bin/dmcli -t ${D}/usr/ccsp
}

PACKAGES += "${PN}-ccsp"

FILES_${PN} = " \
    ${bindir}/dmcli \
    ${prefix}/ccsp/dmcli \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
