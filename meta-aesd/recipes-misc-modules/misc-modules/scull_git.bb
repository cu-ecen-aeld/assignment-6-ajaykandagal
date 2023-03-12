# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-ajaykandagal.git;protocol=ssh;branch=master"

# Modify these as desired
PV = "1.0+git${SRCPV}"
# TODO: set to reference a specific commit hash in your assignment repo
SRCREV = "1591938fcf389ebe5e0e1e0c87482b58b8f6adfe"

# This sets your staging directory based on WORKDIR, where WORKDIR is defined at 
# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-WORKDIR
# We reference the "misc-modules" directory here to build from the "misc-modules" directory
# in your assignments repo
S = "${WORKDIR}/git/misc-modules"

# TODO: Add the misc-modules modules and any other files you need to install
# See https://git.yoctoproject.org/poky/plain/meta/conf/bitbake.conf?h=kirkstone
FILES:${PN} += "${bindir}/misc-modules"

inherit module

EXTRA_OEMAKE_append_task-install = " -C ${STAGING_KERNEL_DIR} M=${S}"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	# TODO: Install your binaries/scripts here.
	# Be sure to install the target directory with install -d first
	# Yocto variables ${D} and ${S} are useful here, which you can read about at 
	# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-D
	# and
	# https://docs.yoctoproject.org/ref-manual/variables.html?highlight=workdir#term-S
	# See example at https://github.com/cu-ecen-aeld/ecen5013-yocto/blob/ecen5013-hello-world/meta-ecen5013/recipes-ecen5013/ecen5013-hello-world/ecen5013-hello-world_git.bb
	install -d ${D}${bindir}

    install -m 0755 ${S}/misc-module/faulty.ko  ${D}${bindir}/
	install -m 0755 ${S}/misc-module/hello.ko  ${D}${bindir}/
	install -m 0755 ${S}/misc-module/module_load  ${D}${bindir}/
	install -m 0755 ${S}/misc-module/module_unload  ${D}${bindir}/
}
