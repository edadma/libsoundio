package io.github.edadma.libsoundio.extern

import scala.scalanative.unsafe._

@link("libsoundio")
@extern
object LibSoundio {

  type SoundIo = extern

  type SoundIoChannelLayoutS = CStruct[]

  type SoundIoDeviceS = CStruct19[SoundIo, CString, CString, CInt, ]

  implicit class SoundIoDeviceOps(device: Ptr[SoundIoDeviceS]) {
    def soundio: SoundIo = device._1
    def id: CString      = device._2
    def name: CString    = device._3
    def aim: SoundIoDeviceAim        = device._4 //enum SoundIoDeviceAim
  }

  def soundio_create: SoundIo                                     = extern
  def soundio_connect(soundio: SoundIo): CInt                     = extern
  def soundio_flush_events(soundio: SoundIo): Unit                = extern
  def soundio_default_output_device_index(soundio: SoundIo): CInt = extern

}
