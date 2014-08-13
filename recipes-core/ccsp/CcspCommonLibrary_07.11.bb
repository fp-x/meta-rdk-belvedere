SUMMARY = "CCSP libccsp_common component"
HOMEPAGE = "http://github.com/ccsp-yocto/CcspCommonLibrary"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "dbus"

SRC_URI = "\
git://github.com/ccsp-yocto/CcspCommonLibrary.git;protocol=git;branch=master \
    "
SRCREV="75b957d28065c727c17c62f3c8e33ed2d68d0966"

SRC_URI[md5sum] = "d338d61e396d5038025339bf5bdb169d"
SRC_URI[sha256sum] = "e6f5a166c0e0f775dc09261f992abb561b781f4a992ef2c0081edcf6b265df24"

S = "${WORKDIR}/git"

inherit autotools

export LDFLAGS = "-L${STAGING_DIR_HOST}/lib \
                  -L${STAGING_DIR_HOST}/usr/lib \
                 "
export INCLUDES = " -I${STAGING_DIR_HOST}/usr/include/dbus-1.0 \
 -I${STAGING_DIR_HOST}/usr/lib/dbus-1.0/include \
 -I${S}/source/ccsp/custom \
 -I${S}/source/ccsp/include \
 -I${S}/source/ccsp/components/common/MessageBusHelper/include \
 -I${S}/source/ccsp/components/include \
 -I${S}/source/cosa/custom \
 -I${S}/source/cosa/include \
 -I${S}/source/cosa/include/linux \
 -I${S}/source/cosa/package/bmc2/include \
 -I${S}/source/cosa/package/bmw2/beep/include \
 -I${S}/source/cosa/package/bmw2/bree/include \
 -I${S}/source/cosa/package/bmw2/bwrm/include \
 -I${S}/source/cosa/package/bmw2/bwsp/include \
 -I${S}/source/cosa/package/bmw2/include \
 -I${S}/source/cosa/package/cli/include \
 -I${S}/source/cosa/package/slap/include \
 -I${S}/source/cosa/package/slap/services/bmc2/include \
 -I${S}/source/cosa/package/slap/services/dslh/include \
 -I${S}/source/cosa/package/system/include \
 -I${S}/source/cosa/utilities/include \
 -I${S}/source/debug_api/include \
 -I${S}/source/util_api/asn.1/include \
 -I${S}/source/util_api/ansc/include \
 -I${S}/source/util_api/http/include \
 -I${S}/source/util_api/http/utilities/HttpSimpleMsgParser \
 -I${S}/source/util_api/http/utilities/include \
 -I${S}/source/util_api/stun/include \
 -I${S}/source/util_api/tls/include \
 -I${S}/source/util_api/web/include \
"

