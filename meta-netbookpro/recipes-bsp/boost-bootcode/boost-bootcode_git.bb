# Copyright (C) 2011 Peter Tworek <tworaz666@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Binary boot code allowing to start linux kernel from BooSt boot loader."

inherit deploy

SRC_URI = "git://github.com/tworaz/boost-bootcode.git;branch=master;protocol=git"
SRCREV = "4c84f3b124e1592b3110c335228eb3a362c72e3b"
PV = "1+git${SRCPV}"
PR = "r1"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=3775480a712fc46a69647678acb234cb"

PACKAGES = ""
COMPATIBLE_HOST = "arm.*-linux"
COMPATIBLE_MACHINE = "netbookpro"
PACKAGE_ARCH = "${MACHINE_ARCH}"

BOOST_KERNEL_CMDLINE ?= 'root=/dev/sda2 rw rootdelay=1 console=tty0 init=/sbin/init quiet'

S = "${WORKDIR}/git"

do_configure () {
    echo "KERNEL_CMDLINE=\"${BOOST_KERNEL_CMDLINE}\"" > ${S}/Makefile.config
}

do_compile () {
    unset LDFLAGS
    unset CPPFLAGS
    oe_runmake CROSS=${TARGET_PREFIX} CPP=${TARGET_PREFIX}cpp
}

do_deploy () {
    install -d ${DEPLOYDIR}
    install ${S}/bcode ${DEPLOYDIR}/bcode-${PV}.bin
    cd ${DEPLOYDIR}
    ln -sf bcode-${PV}.bin bcode-latest.bin
}
addtask deploy before do_build after do_compile
