SUMMARY = "additions to the tr69 recipe to install puma_autoconf.h and autoconf.h needed by CCSP's P and M"

do_install_append () {
	install -d ${D}${includedir}/ccsp
    install -m 644 ${WORKDIR}/umftmp/ti/usr/include/autoconf.h -t ${D}${includedir}/ccsp
    install -m 644 ${INTELSDKPATH}/build/${DISTRO}/configs/puma_autoconf.h ${D}${includedir}/ccsp
}
