# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: MdBarDataBO.proto

from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor.FileDescriptor(
  name='MdBarDataBO.proto',
  package='com.purefun.fams.md',
  syntax='proto2',
  serialized_options=None,
  serialized_pb=b'\n\x11MdBarDataBO.proto\x12\x13\x63om.purefun.fams.md\"\x94\x02\n\x0bMdBarDataBO\x12\x0c\n\x04uuid\x18\x01 \x01(\t\x12\x0c\n\x04\x62oid\x18\x02 \x01(\x12\x12\x13\n\x0b\x64\x65stination\x18\x03 \x01(\t\x12\x15\n\rsecurity_code\x18\x04 \x01(\t\x12\x0c\n\x04\x65xch\x18\x05 \x01(\t\x12\x15\n\rsecurity_type\x18\x06 \x01(\t\x12\x0c\n\x04\x64\x61te\x18\x07 \x01(\t\x12\x0c\n\x04open\x18\x08 \x01(\x01\x12\x0c\n\x04high\x18\t \x01(\x01\x12\x0b\n\x03low\x18\n \x01(\x01\x12\r\n\x05\x63lose\x18\x0b \x01(\x01\x12\x0e\n\x06volume\x18\x0c \x01(\x12\x12\x0e\n\x06\x63hange\x18\r \x01(\x01\x12\x11\n\tpre_close\x18\x0e \x01(\x01\x12\x0f\n\x07pct_chg\x18\x0f \x01(\x01\x12\x0e\n\x06\x61mount\x18\x10 \x01(\x01'
)




_MDBARDATABO = _descriptor.Descriptor(
  name='MdBarDataBO',
  full_name='com.purefun.fams.md.MdBarDataBO',
  filename=None,
  file=DESCRIPTOR,
  containing_type=None,
  fields=[
    _descriptor.FieldDescriptor(
      name='uuid', full_name='com.purefun.fams.md.MdBarDataBO.uuid', index=0,
      number=1, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='boid', full_name='com.purefun.fams.md.MdBarDataBO.boid', index=1,
      number=2, type=18, cpp_type=2, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='destination', full_name='com.purefun.fams.md.MdBarDataBO.destination', index=2,
      number=3, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='security_code', full_name='com.purefun.fams.md.MdBarDataBO.security_code', index=3,
      number=4, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='exch', full_name='com.purefun.fams.md.MdBarDataBO.exch', index=4,
      number=5, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='security_type', full_name='com.purefun.fams.md.MdBarDataBO.security_type', index=5,
      number=6, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='date', full_name='com.purefun.fams.md.MdBarDataBO.date', index=6,
      number=7, type=9, cpp_type=9, label=1,
      has_default_value=False, default_value=b"".decode('utf-8'),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='open', full_name='com.purefun.fams.md.MdBarDataBO.open', index=7,
      number=8, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='high', full_name='com.purefun.fams.md.MdBarDataBO.high', index=8,
      number=9, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='low', full_name='com.purefun.fams.md.MdBarDataBO.low', index=9,
      number=10, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='close', full_name='com.purefun.fams.md.MdBarDataBO.close', index=10,
      number=11, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='volume', full_name='com.purefun.fams.md.MdBarDataBO.volume', index=11,
      number=12, type=18, cpp_type=2, label=1,
      has_default_value=False, default_value=0,
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='change', full_name='com.purefun.fams.md.MdBarDataBO.change', index=12,
      number=13, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='pre_close', full_name='com.purefun.fams.md.MdBarDataBO.pre_close', index=13,
      number=14, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='pct_chg', full_name='com.purefun.fams.md.MdBarDataBO.pct_chg', index=14,
      number=15, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
    _descriptor.FieldDescriptor(
      name='amount', full_name='com.purefun.fams.md.MdBarDataBO.amount', index=15,
      number=16, type=1, cpp_type=5, label=1,
      has_default_value=False, default_value=float(0),
      message_type=None, enum_type=None, containing_type=None,
      is_extension=False, extension_scope=None,
      serialized_options=None, file=DESCRIPTOR),
  ],
  extensions=[
  ],
  nested_types=[],
  enum_types=[
  ],
  serialized_options=None,
  is_extendable=False,
  syntax='proto2',
  extension_ranges=[],
  oneofs=[
  ],
  serialized_start=43,
  serialized_end=319,
)

DESCRIPTOR.message_types_by_name['MdBarDataBO'] = _MDBARDATABO
_sym_db.RegisterFileDescriptor(DESCRIPTOR)

MdBarDataBO = _reflection.GeneratedProtocolMessageType('MdBarDataBO', (_message.Message,), {
  'DESCRIPTOR' : _MDBARDATABO,
  '__module__' : 'MdBarDataBO_pb2'
  # @@protoc_insertion_point(class_scope:com.purefun.fams.md.MdBarDataBO)
  })
_sym_db.RegisterMessage(MdBarDataBO)


# @@protoc_insertion_point(module_scope)
