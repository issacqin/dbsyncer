// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: BinlogMessageProto.proto

package org.dbsyncer.storage.binlog.proto;

/**
 * Protobuf type {@code BinlogMessage}
 */
public final class BinlogMessage extends
        com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:BinlogMessage)
        BinlogMessageOrBuilder {
  private static final long serialVersionUID = 0L;

  // Use BinlogMessage.newBuilder() to construct.
  private BinlogMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  private BinlogMessage() {
    tableGroupId_ = "";
    event_ = 0;
    data_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
          UnusedPrivateParameter unused) {
    return new BinlogMessage();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }

  private BinlogMessage(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
            com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            tableGroupId_ = s;
            break;
          }
          case 16: {
            int rawValue = input.readEnum();

            event_ = rawValue;
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              data_ = new java.util.ArrayList<org.dbsyncer.storage.binlog.proto.Data>();
              mutable_bitField0_ |= 0x00000001;
            }
            data_.add(
                    input.readMessage(org.dbsyncer.storage.binlog.proto.Data.parser(), extensionRegistry));
            break;
          }
          default: {
            if (!parseUnknownField(
                    input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (com.google.protobuf.UninitializedMessageException e) {
      throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
              e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        data_ = java.util.Collections.unmodifiableList(data_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }

  public static final com.google.protobuf.Descriptors.Descriptor
  getDescriptor() {
    return org.dbsyncer.storage.binlog.proto.BinlogMessageProto.internal_static_BinlogMessage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
  internalGetFieldAccessorTable() {
    return org.dbsyncer.storage.binlog.proto.BinlogMessageProto.internal_static_BinlogMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                    org.dbsyncer.storage.binlog.proto.BinlogMessage.class, org.dbsyncer.storage.binlog.proto.BinlogMessage.Builder.class);
  }

  public static final int TABLE_GROUP_ID_FIELD_NUMBER = 1;
  private volatile java.lang.Object tableGroupId_;

  /**
   * <code>string table_group_id = 1;</code>
   *
   * @return The tableGroupId.
   */
  @java.lang.Override
  public java.lang.String getTableGroupId() {
    java.lang.Object ref = tableGroupId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      tableGroupId_ = s;
      return s;
    }
  }

  /**
   * <code>string table_group_id = 1;</code>
   *
   * @return The bytes for tableGroupId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
  getTableGroupIdBytes() {
    java.lang.Object ref = tableGroupId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b =
              com.google.protobuf.ByteString.copyFromUtf8(
                      (java.lang.String) ref);
      tableGroupId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int EVENT_FIELD_NUMBER = 2;
  private int event_;

  /**
   * <code>.Event event = 2;</code>
   *
   * @return The enum numeric value on the wire for event.
   */
  @java.lang.Override
  public int getEventValue() {
    return event_;
  }

  /**
   * <code>.Event event = 2;</code>
   *
   * @return The event.
   */
  @java.lang.Override
  public org.dbsyncer.storage.binlog.proto.Event getEvent() {
    @SuppressWarnings("deprecation")
    org.dbsyncer.storage.binlog.proto.Event result = org.dbsyncer.storage.binlog.proto.Event.valueOf(event_);
    return result == null ? org.dbsyncer.storage.binlog.proto.Event.UNRECOGNIZED : result;
  }

  public static final int DATA_FIELD_NUMBER = 3;
  private java.util.List<org.dbsyncer.storage.binlog.proto.Data> data_;

  /**
   * <code>repeated .Data data = 3;</code>
   */
  @java.lang.Override
  public java.util.List<org.dbsyncer.storage.binlog.proto.Data> getDataList() {
    return data_;
  }

  /**
   * <code>repeated .Data data = 3;</code>
   */
  @java.lang.Override
  public java.util.List<? extends org.dbsyncer.storage.binlog.proto.DataOrBuilder>
  getDataOrBuilderList() {
    return data_;
  }

  /**
   * <code>repeated .Data data = 3;</code>
   */
  @java.lang.Override
  public int getDataCount() {
    return data_.size();
  }

  /**
   * <code>repeated .Data data = 3;</code>
   */
  @java.lang.Override
  public org.dbsyncer.storage.binlog.proto.Data getData(int index) {
    return data_.get(index);
  }

  /**
   * <code>repeated .Data data = 3;</code>
   */
  @java.lang.Override
  public org.dbsyncer.storage.binlog.proto.DataOrBuilder getDataOrBuilder(
          int index) {
    return data_.get(index);
  }

  private byte memoizedIsInitialized = -1;

  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
          throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(tableGroupId_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, tableGroupId_);
    }
    if (event_ != org.dbsyncer.storage.binlog.proto.Event.UPDATE.getNumber()) {
      output.writeEnum(2, event_);
    }
    for (int i = 0; i < data_.size(); i++) {
      output.writeMessage(3, data_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(tableGroupId_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, tableGroupId_);
    }
    if (event_ != org.dbsyncer.storage.binlog.proto.Event.UPDATE.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
              .computeEnumSize(2, event_);
    }
    for (int i = 0; i < data_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
              .computeMessageSize(3, data_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof org.dbsyncer.storage.binlog.proto.BinlogMessage)) {
      return super.equals(obj);
    }
    org.dbsyncer.storage.binlog.proto.BinlogMessage other = (org.dbsyncer.storage.binlog.proto.BinlogMessage) obj;

    if (!getTableGroupId()
            .equals(other.getTableGroupId())) return false;
    if (event_ != other.event_) return false;
    if (!getDataList()
            .equals(other.getDataList())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + TABLE_GROUP_ID_FIELD_NUMBER;
    hash = (53 * hash) + getTableGroupId().hashCode();
    hash = (37 * hash) + EVENT_FIELD_NUMBER;
    hash = (53 * hash) + event_;
    if (getDataCount() > 0) {
      hash = (37 * hash) + DATA_FIELD_NUMBER;
      hash = (53 * hash) + getDataList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.dbsyncer.storage.binlog.proto.BinlogMessage parseFrom(
          java.nio.ByteBuffer data)
          throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static org.dbsyncer.storage.binlog.proto.BinlogMessage parseFrom(
          java.nio.ByteBuffer data,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static org.dbsyncer.storage.binlog.proto.BinlogMessage parseFrom(
          com.google.protobuf.ByteString data)
          throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static org.dbsyncer.storage.binlog.proto.BinlogMessage parseFrom(
          com.google.protobuf.ByteString data,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static org.dbsyncer.storage.binlog.proto.BinlogMessage parseFrom(byte[] data)
          throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static org.dbsyncer.storage.binlog.proto.BinlogMessage parseFrom(
          byte[] data,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static org.dbsyncer.storage.binlog.proto.BinlogMessage parseFrom(java.io.InputStream input)
          throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
            .parseWithIOException(PARSER, input);
  }

  public static org.dbsyncer.storage.binlog.proto.BinlogMessage parseFrom(
          java.io.InputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
            .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static org.dbsyncer.storage.binlog.proto.BinlogMessage parseDelimitedFrom(java.io.InputStream input)
          throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
            .parseDelimitedWithIOException(PARSER, input);
  }

  public static org.dbsyncer.storage.binlog.proto.BinlogMessage parseDelimitedFrom(
          java.io.InputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
            .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }

  public static org.dbsyncer.storage.binlog.proto.BinlogMessage parseFrom(
          com.google.protobuf.CodedInputStream input)
          throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
            .parseWithIOException(PARSER, input);
  }

  public static org.dbsyncer.storage.binlog.proto.BinlogMessage parseFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
            .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() {
    return newBuilder();
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }

  public static Builder newBuilder(org.dbsyncer.storage.binlog.proto.BinlogMessage prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
            ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }

  /**
   * Protobuf type {@code BinlogMessage}
   */
  public static final class Builder extends
          com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
          // @@protoc_insertion_point(builder_implements:BinlogMessage)
          org.dbsyncer.storage.binlog.proto.BinlogMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
    getDescriptor() {
      return org.dbsyncer.storage.binlog.proto.BinlogMessageProto.internal_static_BinlogMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
    internalGetFieldAccessorTable() {
      return org.dbsyncer.storage.binlog.proto.BinlogMessageProto.internal_static_BinlogMessage_fieldAccessorTable
              .ensureFieldAccessorsInitialized(
                      org.dbsyncer.storage.binlog.proto.BinlogMessage.class, org.dbsyncer.storage.binlog.proto.BinlogMessage.Builder.class);
    }

    // Construct using org.dbsyncer.storage.binlog.proto.BinlogMessage.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
            com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }

    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getDataFieldBuilder();
      }
    }

    @java.lang.Override
    public Builder clear() {
      super.clear();
      tableGroupId_ = "";

      event_ = 0;

      if (dataBuilder_ == null) {
        data_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        dataBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
    getDescriptorForType() {
      return org.dbsyncer.storage.binlog.proto.BinlogMessageProto.internal_static_BinlogMessage_descriptor;
    }

    @java.lang.Override
    public org.dbsyncer.storage.binlog.proto.BinlogMessage getDefaultInstanceForType() {
      return org.dbsyncer.storage.binlog.proto.BinlogMessage.getDefaultInstance();
    }

    @java.lang.Override
    public org.dbsyncer.storage.binlog.proto.BinlogMessage build() {
      org.dbsyncer.storage.binlog.proto.BinlogMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.dbsyncer.storage.binlog.proto.BinlogMessage buildPartial() {
      org.dbsyncer.storage.binlog.proto.BinlogMessage result = new org.dbsyncer.storage.binlog.proto.BinlogMessage(this);
      int from_bitField0_ = bitField0_;
      result.tableGroupId_ = tableGroupId_;
      result.event_ = event_;
      if (dataBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          data_ = java.util.Collections.unmodifiableList(data_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.data_ = data_;
      } else {
        result.data_ = dataBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }

    @java.lang.Override
    public Builder setField(
            com.google.protobuf.Descriptors.FieldDescriptor field,
            java.lang.Object value) {
      return super.setField(field, value);
    }

    @java.lang.Override
    public Builder clearField(
            com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }

    @java.lang.Override
    public Builder clearOneof(
            com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }

    @java.lang.Override
    public Builder setRepeatedField(
            com.google.protobuf.Descriptors.FieldDescriptor field,
            int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }

    @java.lang.Override
    public Builder addRepeatedField(
            com.google.protobuf.Descriptors.FieldDescriptor field,
            java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.dbsyncer.storage.binlog.proto.BinlogMessage) {
        return mergeFrom((org.dbsyncer.storage.binlog.proto.BinlogMessage) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.dbsyncer.storage.binlog.proto.BinlogMessage other) {
      if (other == org.dbsyncer.storage.binlog.proto.BinlogMessage.getDefaultInstance()) return this;
      if (!other.getTableGroupId().isEmpty()) {
        tableGroupId_ = other.tableGroupId_;
        onChanged();
      }
      if (other.event_ != 0) {
        setEventValue(other.getEventValue());
      }
      if (dataBuilder_ == null) {
        if (!other.data_.isEmpty()) {
          if (data_.isEmpty()) {
            data_ = other.data_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureDataIsMutable();
            data_.addAll(other.data_);
          }
          onChanged();
        }
      } else {
        if (!other.data_.isEmpty()) {
          if (dataBuilder_.isEmpty()) {
            dataBuilder_.dispose();
            dataBuilder_ = null;
            data_ = other.data_;
            bitField0_ = (bitField0_ & ~0x00000001);
            dataBuilder_ =
                    com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                            getDataFieldBuilder() : null;
          } else {
            dataBuilder_.addAllMessages(other.data_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
      org.dbsyncer.storage.binlog.proto.BinlogMessage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.dbsyncer.storage.binlog.proto.BinlogMessage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int bitField0_;

    private java.lang.Object tableGroupId_ = "";

    /**
     * <code>string table_group_id = 1;</code>
     *
     * @return The tableGroupId.
     */
    public java.lang.String getTableGroupId() {
      java.lang.Object ref = tableGroupId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
                (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        tableGroupId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }

    /**
     * <code>string table_group_id = 1;</code>
     *
     * @return The bytes for tableGroupId.
     */
    public com.google.protobuf.ByteString
    getTableGroupIdBytes() {
      java.lang.Object ref = tableGroupId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
                com.google.protobuf.ByteString.copyFromUtf8(
                        (java.lang.String) ref);
        tableGroupId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    /**
     * <code>string table_group_id = 1;</code>
     *
     * @param value The tableGroupId to set.
     * @return This builder for chaining.
     */
    public Builder setTableGroupId(
            java.lang.String value) {
      if (value == null) {
        throw new NullPointerException();
      }

      tableGroupId_ = value;
      onChanged();
      return this;
    }

    /**
     * <code>string table_group_id = 1;</code>
     *
     * @return This builder for chaining.
     */
    public Builder clearTableGroupId() {

      tableGroupId_ = getDefaultInstance().getTableGroupId();
      onChanged();
      return this;
    }

    /**
     * <code>string table_group_id = 1;</code>
     *
     * @param value The bytes for tableGroupId to set.
     * @return This builder for chaining.
     */
    public Builder setTableGroupIdBytes(
            com.google.protobuf.ByteString value) {
      if (value == null) {
        throw new NullPointerException();
      }
      checkByteStringIsUtf8(value);

      tableGroupId_ = value;
      onChanged();
      return this;
    }

    private int event_ = 0;

    /**
     * <code>.Event event = 2;</code>
     *
     * @return The enum numeric value on the wire for event.
     */
    @java.lang.Override
    public int getEventValue() {
      return event_;
    }

    /**
     * <code>.Event event = 2;</code>
     *
     * @param value The enum numeric value on the wire for event to set.
     * @return This builder for chaining.
     */
    public Builder setEventValue(int value) {

      event_ = value;
      onChanged();
      return this;
    }

    /**
     * <code>.Event event = 2;</code>
     *
     * @return The event.
     */
    @java.lang.Override
    public org.dbsyncer.storage.binlog.proto.Event getEvent() {
      @SuppressWarnings("deprecation")
      org.dbsyncer.storage.binlog.proto.Event result = org.dbsyncer.storage.binlog.proto.Event.valueOf(event_);
      return result == null ? org.dbsyncer.storage.binlog.proto.Event.UNRECOGNIZED : result;
    }

    /**
     * <code>.Event event = 2;</code>
     *
     * @param value The event to set.
     * @return This builder for chaining.
     */
    public Builder setEvent(org.dbsyncer.storage.binlog.proto.Event value) {
      if (value == null) {
        throw new NullPointerException();
      }

      event_ = value.getNumber();
      onChanged();
      return this;
    }

    /**
     * <code>.Event event = 2;</code>
     *
     * @return This builder for chaining.
     */
    public Builder clearEvent() {

      event_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<org.dbsyncer.storage.binlog.proto.Data> data_ =
            java.util.Collections.emptyList();

    private void ensureDataIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        data_ = new java.util.ArrayList<org.dbsyncer.storage.binlog.proto.Data>(data_);
        bitField0_ |= 0x00000001;
      }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
            org.dbsyncer.storage.binlog.proto.Data, org.dbsyncer.storage.binlog.proto.Data.Builder, org.dbsyncer.storage.binlog.proto.DataOrBuilder> dataBuilder_;

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public java.util.List<org.dbsyncer.storage.binlog.proto.Data> getDataList() {
      if (dataBuilder_ == null) {
        return java.util.Collections.unmodifiableList(data_);
      } else {
        return dataBuilder_.getMessageList();
      }
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public int getDataCount() {
      if (dataBuilder_ == null) {
        return data_.size();
      } else {
        return dataBuilder_.getCount();
      }
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public org.dbsyncer.storage.binlog.proto.Data getData(int index) {
      if (dataBuilder_ == null) {
        return data_.get(index);
      } else {
        return dataBuilder_.getMessage(index);
      }
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public Builder setData(
            int index, org.dbsyncer.storage.binlog.proto.Data value) {
      if (dataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDataIsMutable();
        data_.set(index, value);
        onChanged();
      } else {
        dataBuilder_.setMessage(index, value);
      }
      return this;
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public Builder setData(
            int index, org.dbsyncer.storage.binlog.proto.Data.Builder builderForValue) {
      if (dataBuilder_ == null) {
        ensureDataIsMutable();
        data_.set(index, builderForValue.build());
        onChanged();
      } else {
        dataBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public Builder addData(org.dbsyncer.storage.binlog.proto.Data value) {
      if (dataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDataIsMutable();
        data_.add(value);
        onChanged();
      } else {
        dataBuilder_.addMessage(value);
      }
      return this;
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public Builder addData(
            int index, org.dbsyncer.storage.binlog.proto.Data value) {
      if (dataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDataIsMutable();
        data_.add(index, value);
        onChanged();
      } else {
        dataBuilder_.addMessage(index, value);
      }
      return this;
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public Builder addData(
            org.dbsyncer.storage.binlog.proto.Data.Builder builderForValue) {
      if (dataBuilder_ == null) {
        ensureDataIsMutable();
        data_.add(builderForValue.build());
        onChanged();
      } else {
        dataBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public Builder addData(
            int index, org.dbsyncer.storage.binlog.proto.Data.Builder builderForValue) {
      if (dataBuilder_ == null) {
        ensureDataIsMutable();
        data_.add(index, builderForValue.build());
        onChanged();
      } else {
        dataBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public Builder addAllData(
            java.lang.Iterable<? extends org.dbsyncer.storage.binlog.proto.Data> values) {
      if (dataBuilder_ == null) {
        ensureDataIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
                values, data_);
        onChanged();
      } else {
        dataBuilder_.addAllMessages(values);
      }
      return this;
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public Builder clearData() {
      if (dataBuilder_ == null) {
        data_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        dataBuilder_.clear();
      }
      return this;
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public Builder removeData(int index) {
      if (dataBuilder_ == null) {
        ensureDataIsMutable();
        data_.remove(index);
        onChanged();
      } else {
        dataBuilder_.remove(index);
      }
      return this;
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public org.dbsyncer.storage.binlog.proto.Data.Builder getDataBuilder(
            int index) {
      return getDataFieldBuilder().getBuilder(index);
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public org.dbsyncer.storage.binlog.proto.DataOrBuilder getDataOrBuilder(
            int index) {
      if (dataBuilder_ == null) {
        return data_.get(index);
      } else {
        return dataBuilder_.getMessageOrBuilder(index);
      }
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public java.util.List<? extends org.dbsyncer.storage.binlog.proto.DataOrBuilder>
    getDataOrBuilderList() {
      if (dataBuilder_ != null) {
        return dataBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(data_);
      }
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public org.dbsyncer.storage.binlog.proto.Data.Builder addDataBuilder() {
      return getDataFieldBuilder().addBuilder(
              org.dbsyncer.storage.binlog.proto.Data.getDefaultInstance());
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public org.dbsyncer.storage.binlog.proto.Data.Builder addDataBuilder(
            int index) {
      return getDataFieldBuilder().addBuilder(
              index, org.dbsyncer.storage.binlog.proto.Data.getDefaultInstance());
    }

    /**
     * <code>repeated .Data data = 3;</code>
     */
    public java.util.List<org.dbsyncer.storage.binlog.proto.Data.Builder>
    getDataBuilderList() {
      return getDataFieldBuilder().getBuilderList();
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
            org.dbsyncer.storage.binlog.proto.Data, org.dbsyncer.storage.binlog.proto.Data.Builder, org.dbsyncer.storage.binlog.proto.DataOrBuilder>
    getDataFieldBuilder() {
      if (dataBuilder_ == null) {
        dataBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
                org.dbsyncer.storage.binlog.proto.Data, org.dbsyncer.storage.binlog.proto.Data.Builder, org.dbsyncer.storage.binlog.proto.DataOrBuilder>(
                data_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        data_ = null;
      }
      return dataBuilder_;
    }

    @java.lang.Override
    public final Builder setUnknownFields(
            final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
            final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:BinlogMessage)
  }

  // @@protoc_insertion_point(class_scope:BinlogMessage)
  private static final org.dbsyncer.storage.binlog.proto.BinlogMessage DEFAULT_INSTANCE;

  static {
    DEFAULT_INSTANCE = new org.dbsyncer.storage.binlog.proto.BinlogMessage();
  }

  public static org.dbsyncer.storage.binlog.proto.BinlogMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<BinlogMessage>
          PARSER = new com.google.protobuf.AbstractParser<BinlogMessage>() {
    @java.lang.Override
    public BinlogMessage parsePartialFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return new BinlogMessage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BinlogMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BinlogMessage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.dbsyncer.storage.binlog.proto.BinlogMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

