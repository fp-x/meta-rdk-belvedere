SUMMARY = "CCSP Utopia"
HOMEPAGE = "http://github.com/belvedere-yocto/Utopia"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library hal zlib"

SRC_URI = "\
    git://github.com/belvedere-yocto/Utopia.git;protocol=git;branch=${CCSP_GIT_BRANCH} \
    "

SRCREV = "${AUTOREV}"
PV = "${RDK_RELEASE}+git${SRCPV}"

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
    install -D -p -m 644 source/include/autoconf.h ${D}${includedir}/utctx/autoconf.h
    install -D -p -m 644 source/ulog/ulog.h ${D}${includedir}/ulog/ulog.h
    install -D -p -m 644 source/utapi/lib/utapi.h ${D}${includedir}/utapi/utapi.h
    install -D -p -m 644 source/utapi/lib/utapi_wlan.h ${D}${includedir}/utapi/utapi_wlan.h
    install -D -p -m 644 source/utapi/lib/utapi_util.h ${D}${includedir}/utapi/utapi_util.h
    install -D -p -m 644 source/utctx/lib/utctx.h ${D}${includedir}/utctx/utctx.h
    install -D -p -m 644 source/utctx/lib/utctx_api.h ${D}${includedir}/utctx/utctx_api.h
    install -D -p -m 644 source/utctx/lib/utctx_rwlock.h ${D}${includedir}/utctx/utctx_rwlock.h
    install -D -p -m 644 source/syscfg/lib/syscfg.h ${D}${includedir}/syscfg/syscfg.h
    install -D -p -m 644 source/sysevent/lib/sysevent.h ${D}${includedir}/sysevent/sysevent.h
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
