
#require ${HOME}/poky/meta-raspberrypi/recipes-core/images/rpi-basic-image.bb
require ${HOME}/poky/meta/recipes-core/images/core-image-minimal.bb

RDEPENDS_${PN} += " \
	CcspCommonLibrary \
	hal \
	CcspCMAgent \
	CcspCr \
	CcspLMLite \
	CcspMisc \
	CcspMtaAgent \
	CcspPandM \
	CcspPsm \
	CcspSnmpPa \
	CcspTr069Pa \
	CcspWifiAgent \
	RebootManager \
	TestAndDiagnostic \
	"

IMAGE_INSTALL += " \
	CcspCommonLibrary \
	hal \
	CcspCMAgent \
	CcspCr \
	CcspLMLite \
	CcspMisc \
	CcspMtaAgent \
	CcspPandM \
	CcspPsm \
	CcspSnmpPa \
	CcspTr069Pa \
	CcspWifiAgent \
	RebootManager \
	TestAndDiagnostic \
	"

export IMAGE_BASENAME = "ccsp-test-image"

