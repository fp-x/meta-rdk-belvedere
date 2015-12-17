SUMMARY = "CCSP GWProvAPP"
HOMEPAGE = "https://github.com/belvedere-yocto/GwProvApp"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=175792518e4ac015ab6696d16c4f607e"

DEPENDS = "ccsp-common-library virtual/ccsp-hal ruli utopia"
require ccsp_common.inc

SRC_URI = "${RDKB_CCSP_ROOT_GIT}/GwProvApp${CCSP_EXT};protocol=${RDK_GIT_PROTOCOL};branch=${CCSP_GIT_BRANCH};name=GwProvApp"

SRCREV_GwProvApp = "${AUTOREV}"
PV = "${RDK_RELEASE}+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools

do_install_append () {
    # Config files and scripts
    install -d ${D}/${includedir}
    install -m 644 ${S}/source/include/Tr69_Tlv.h -t ${D}/${includedir}
}


do_install_append_armeb () {
    # Config files and scripts
    install -d ${D}/usr/ccsp/
    ln -sf ${D}/usr/bin/gw_prov_utopia ${D}/usr/ccsp/gw_prov_utopia
}

PACKAGES += "${PN}-ccsp"

FILES_${PN} = " /usr/ccsp/"
