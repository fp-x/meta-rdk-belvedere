SUMMARY = "CCSP libccsp_common component"
HOMEPAGE = "http://github.com/ccsp-yocto/CcspCommonLibrary"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "dbus"

SRC_URI = "\
git://github.com/ccsp-yocto/CcspCommonLibrary.git;protocol=git;branch=daisy \
file://01-support-newer-dbus-apis.patch \
    "
SRCREV="7bdaa99b186d9a30f0e286f2bb95dcb45e3c98a2"

SRC_URI[md5sum] = "d338d61e396d5038025339bf5bdb169d"
SRC_URI[sha256sum] = "e6f5a166c0e0f775dc09261f992abb561b781f4a992ef2c0081edcf6b265df24"

S = "${WORKDIR}/git"

inherit autotools

PACKAGECONFIG ??= "dbus"

export INCLUDES = " -I${STAGING_DIR_HOST}/usr/include/dbus-1.0 \
 -I${STAGING_DIR_HOST}/usr/lib/dbus-1.0/include \
"

export LDFLAGS = " -L${STAGING_DIR_HOST}/usr/lib \
 -ldbus-1 \
"

