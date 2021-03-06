SUMMARY = "CCSP WebUI component"
HOMEPAGE = "http://github.com/ccsp-yocto/webui"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../../README;beginline=1;endline=18;md5=17835e4bef073e5070ff4a4fc79fe537"

DEPENDS = "ccsp-common-library php php-native"
require ccsp_common.inc
SRC_URI = "\
    ${RDKB_CCSP_ROOT_GIT}/webui${CCSP_EXT};protocol=${RDK_GIT_PROTOCOL};branch=${CCSP_GIT_BRANCH};name=webui \
    file://LICENSE \
    file://cosalogs.service \
    file://cosalogs.sh \
    file://ert-start.sh \
    file://dbus-start.sh \
    "

SRCREV = "${AUTOREV}"
SRCREV_xb3 = "${AUTOREV}"
SRCREV_FORMAT = "default_xb3"

PV = "${RDK_RELEASE}+git${SRCPV}"

S = "${WORKDIR}/git/source/CcspPhpExtension/"

inherit autotools systemd

CFLAGS += " \
    -I${STAGING_INCDIR}/dbus-1.0 \
    -I${STAGING_LIBDIR}/dbus-1.0/include \
    -I${STAGING_INCDIR}/ccsp \
    -fPIC \
    "

LDFLAGS += " \
     -ldbus-1 \
     "

do_configure_prepend () {
	cp -fr ${WORKDIR}/LICENSE ${S}
	(cd ${S} && phpize && aclocal && libtoolize --force && autoreconf)
}

EXTRA_OECONF = "--enable-cosa CCSP_COMMON_LIB=${STAGING_LIBDIR}"

do_configure () {
	oe_runconf
}

do_install() {
	#oe_runmake install DESTDIR=${D} INSTALL_ROOT=${D}
	install -d ${D}${base_libdir}/rdk
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/cosalogs.service ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/cosalogs.sh ${D}${base_libdir}/rdk
    install -d ${D}/fss/gw/etc
    install -d ${D}/fss/gw/usr/www
    install -d ${D}/fss/gw/usr/ccsp
    cp -r ${WORKDIR}/git/source/Styles/xb3/code/* ${D}/fss/gw/usr/www/
    install ${WORKDIR}/git/source/Styles/xb3/config/php.ini ${D}/fss/gw/etc/
    install ${WORKDIR}/ert-start.sh ${D}/fss/gw/usr/ccsp/
    install ${WORKDIR}/dbus-start.sh ${D}/fss/gw/usr/ccsp/
    install ${S}/modules/*.so ${D}/fss/gw/usr/ccsp/
    install -d ${D}/var/ccsp/
    touch ${D}/var/ccsp/ui_dev_debug
    touch ${D}/var/ccsp/ui_dev_mode
    touch ${D}/var/ccsp/cosa_php_debug
}

SYSTEMD_SERVICE_${PN} = "cosalogs.service"

FILES_${PN} += " \
     ${systemd_unitdir}/system/cosalogs.service \
     "
FILES_${PN} += "/fss/* /fss/gw/* /fss/gw/usr/* /fss/gw/usr/ccsp/* /opt/www/* ${base_libdir}/rdk/* ${libdir}"
# /var/* /var/tmp/logs/* 
FILES_${PN} += " ${systemd_unitdir}/system/cosalogs.service "
FILES_${PN}-dbg += "/fss/gw/usr/ccsp/.debug/* /usr/lib/extensions/no-debug-non-zts-20100525/.debug/*"
FILES_${PN}-dbg += "${libdir}/php5/extensions/*/.debug/* \
                    ${libdir}/extensions/*/.debug/*"

