SUMMARY = "CCSP Utopia"
HOMEPAGE = "http://github.com/ccsp-yocto/Utopia"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library hal zlib"

SRC_URI = "\
    git://github.com/ccsp-yocto/Utopia.git;protocol=git;branch=daisy;rev=daisy \
    file://0001-autotools-Use-internal-lsyscfg-lsysevent-lulog-libra.patch \
    "

SRC_URI[md5sum] = "d338d61e396d5038025339bf5bdb169d"
SRC_URI[sha256sum] = "e6f5a166c0e0f775dc09261f992abb561b781f4a992ef2c0081edcf6b265df24"

S = "${WORKDIR}/git"

inherit autotools

CFLAGS_append = " \
    -I=${includedir}/ccsp \
    "
do_compile () {
    oe_runmake -C source/ulog
    oe_runmake -C source/syscfg
    oe_runmake -C source/sysevent
    oe_runmake -C source/services
    oe_runmake -C source/utctx/lib
    oe_runmake -C source/utapi/lib
    oe_runmake all
}

do_install_append () {
    install -D -p -m 777 source/ulog/.libs/*.so* ${D}${libdir}
    install -D -p -m 777 source/syscfg/lib/.libs/*.so* ${D}${libdir}
    install -D -p -m 777 source/sysevent/lib/.libs/*.so* ${D}${libdir}
    install -D -p -m 777 source/services/lib/.libs/*.so* ${D}${libdir}
    install -D -p -m 777 source/utctx/lib/.libs/*.so* ${D}${libdir}
    install -D -p -m 777 source/utapi/lib/.libs/*.so* ${D}${libdir}
    install -D -p -m 644 source/utapi/lib/utapi_wlan.h ${D}${includedir}/utapi/utapi_wlan.h
    install -D -p -m 644 source/syscfg/lib/syscfg.h ${D}${includedir}/syscfg/syscfg.h
    install -D -p -m 644 source/sysevent/lib/libsysevent_internal.h ${D}${includedir}/sysevent/libsysevent_internal.h

}
PACKAGES += "${PN}-ccsp"

FILES_${PN}-ccsp = " \
"

FILES_${PN}-dbg += " \
    ${prefix}/ccsp/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
