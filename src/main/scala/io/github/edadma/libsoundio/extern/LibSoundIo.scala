package io.github.edadma.libsoundio.extern

import io.github.edadma.libsoundio.facade.{SoundIoDeviceAim}

import scala.scalanative.unsafe._

@link("libsoundio")
@extern
object LibSoundio {

  type SoundIoP = Ptr[Byte]

  type SoundIoChannelLayoutS = CStruct[]

  type SoundIoDeviceS = CStruct19[SoundIoP, CString, CString, CInt, Ptr[SoundIoChannelLayoutS], CInt, SoundIoChannelLayoutS]

  implicit class SoundIoDeviceOps(device: Ptr[SoundIoDeviceS]) {
    def soundio: SoundIoP = device._1
    def id: CString      = device._2
    def name: CString    = device._3
    def aim: SoundIoDeviceAim        = new SoundIoDeviceAim(device._4) //enum SoundIoDeviceAim
    def layouts: Ptr[SoundIoChannelLayoutS] = device._5
    def layout_count: CInt = device._6
  def current_layout:SoundIoChannelLayoutS = device._7

  }

  def soundio_create: SoundIoP                                     = extern
  def soundio_connect(soundio: SoundIoP): CInt                     = extern
  def soundio_flush_events(soundio: SoundIoP): Unit                = extern
  def soundio_default_output_device_index(soundio: SoundIoP): CInt = extern

}
