// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MoResponse.proto

package cn.weit.happymo.message;

public final class MoResponse {
  private MoResponse() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface MoResponseMsgOrBuilder extends
      // @@protoc_insertion_point(interface_extends:cn.weit.happymo.message.MoResponseMsg)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>.cn.weit.happymo.message.MsgType msgType = 1;</code>
     */
    int getMsgTypeValue();
    /**
     * <code>.cn.weit.happymo.message.MsgType msgType = 1;</code>
     */
    cn.weit.happymo.message.MsgTypeEnum.MsgType getMsgType();

    /**
     * <code>int32 version = 2;</code>
     */
    int getVersion();

    /**
     * <code>.cn.weit.happymo.message.State state = 3;</code>
     */
    int getStateValue();
    /**
     * <code>.cn.weit.happymo.message.State state = 3;</code>
     */
    cn.weit.happymo.message.ServerState.State getState();

    /**
     * <code>string cause = 4;</code>
     */
    java.lang.String getCause();
    /**
     * <code>string cause = 4;</code>
     */
    com.google.protobuf.ByteString
        getCauseBytes();
  }
  /**
   * Protobuf type {@code cn.weit.happymo.message.MoResponseMsg}
   */
  public  static final class MoResponseMsg extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:cn.weit.happymo.message.MoResponseMsg)
      MoResponseMsgOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use MoResponseMsg.newBuilder() to construct.
    private MoResponseMsg(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private MoResponseMsg() {
      msgType_ = 0;
      state_ = 0;
      cause_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private MoResponseMsg(
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
            case 8: {
              int rawValue = input.readEnum();

              msgType_ = rawValue;
              break;
            }
            case 16: {

              version_ = input.readInt32();
              break;
            }
            case 24: {
              int rawValue = input.readEnum();

              state_ = rawValue;
              break;
            }
            case 34: {
              java.lang.String s = input.readStringRequireUtf8();

              cause_ = s;
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
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return cn.weit.happymo.message.MoResponse.internal_static_cn_weit_happymo_message_MoResponseMsg_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return cn.weit.happymo.message.MoResponse.internal_static_cn_weit_happymo_message_MoResponseMsg_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              cn.weit.happymo.message.MoResponse.MoResponseMsg.class, cn.weit.happymo.message.MoResponse.MoResponseMsg.Builder.class);
    }

    public static final int MSGTYPE_FIELD_NUMBER = 1;
    private int msgType_;
    /**
     * <code>.cn.weit.happymo.message.MsgType msgType = 1;</code>
     */
    public int getMsgTypeValue() {
      return msgType_;
    }
    /**
     * <code>.cn.weit.happymo.message.MsgType msgType = 1;</code>
     */
    public cn.weit.happymo.message.MsgTypeEnum.MsgType getMsgType() {
      @SuppressWarnings("deprecation")
      cn.weit.happymo.message.MsgTypeEnum.MsgType result = cn.weit.happymo.message.MsgTypeEnum.MsgType.valueOf(msgType_);
      return result == null ? cn.weit.happymo.message.MsgTypeEnum.MsgType.UNRECOGNIZED : result;
    }

    public static final int VERSION_FIELD_NUMBER = 2;
    private int version_;
    /**
     * <code>int32 version = 2;</code>
     */
    public int getVersion() {
      return version_;
    }

    public static final int STATE_FIELD_NUMBER = 3;
    private int state_;
    /**
     * <code>.cn.weit.happymo.message.State state = 3;</code>
     */
    public int getStateValue() {
      return state_;
    }
    /**
     * <code>.cn.weit.happymo.message.State state = 3;</code>
     */
    public cn.weit.happymo.message.ServerState.State getState() {
      @SuppressWarnings("deprecation")
      cn.weit.happymo.message.ServerState.State result = cn.weit.happymo.message.ServerState.State.valueOf(state_);
      return result == null ? cn.weit.happymo.message.ServerState.State.UNRECOGNIZED : result;
    }

    public static final int CAUSE_FIELD_NUMBER = 4;
    private volatile java.lang.Object cause_;
    /**
     * <code>string cause = 4;</code>
     */
    public java.lang.String getCause() {
      java.lang.Object ref = cause_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        cause_ = s;
        return s;
      }
    }
    /**
     * <code>string cause = 4;</code>
     */
    public com.google.protobuf.ByteString
        getCauseBytes() {
      java.lang.Object ref = cause_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        cause_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
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
      if (msgType_ != cn.weit.happymo.message.MsgTypeEnum.MsgType.HEARTBEAT.getNumber()) {
        output.writeEnum(1, msgType_);
      }
      if (version_ != 0) {
        output.writeInt32(2, version_);
      }
      if (state_ != cn.weit.happymo.message.ServerState.State.Alive.getNumber()) {
        output.writeEnum(3, state_);
      }
      if (!getCauseBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, cause_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (msgType_ != cn.weit.happymo.message.MsgTypeEnum.MsgType.HEARTBEAT.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(1, msgType_);
      }
      if (version_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, version_);
      }
      if (state_ != cn.weit.happymo.message.ServerState.State.Alive.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(3, state_);
      }
      if (!getCauseBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, cause_);
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
      if (!(obj instanceof cn.weit.happymo.message.MoResponse.MoResponseMsg)) {
        return super.equals(obj);
      }
      cn.weit.happymo.message.MoResponse.MoResponseMsg other = (cn.weit.happymo.message.MoResponse.MoResponseMsg) obj;

      if (msgType_ != other.msgType_) return false;
      if (getVersion()
          != other.getVersion()) return false;
      if (state_ != other.state_) return false;
      if (!getCause()
          .equals(other.getCause())) return false;
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
      hash = (37 * hash) + MSGTYPE_FIELD_NUMBER;
      hash = (53 * hash) + msgType_;
      hash = (37 * hash) + VERSION_FIELD_NUMBER;
      hash = (53 * hash) + getVersion();
      hash = (37 * hash) + STATE_FIELD_NUMBER;
      hash = (53 * hash) + state_;
      hash = (37 * hash) + CAUSE_FIELD_NUMBER;
      hash = (53 * hash) + getCause().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static cn.weit.happymo.message.MoResponse.MoResponseMsg parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static cn.weit.happymo.message.MoResponse.MoResponseMsg parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static cn.weit.happymo.message.MoResponse.MoResponseMsg parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static cn.weit.happymo.message.MoResponse.MoResponseMsg parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static cn.weit.happymo.message.MoResponse.MoResponseMsg parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static cn.weit.happymo.message.MoResponse.MoResponseMsg parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static cn.weit.happymo.message.MoResponse.MoResponseMsg parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static cn.weit.happymo.message.MoResponse.MoResponseMsg parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static cn.weit.happymo.message.MoResponse.MoResponseMsg parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static cn.weit.happymo.message.MoResponse.MoResponseMsg parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static cn.weit.happymo.message.MoResponse.MoResponseMsg parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static cn.weit.happymo.message.MoResponse.MoResponseMsg parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(cn.weit.happymo.message.MoResponse.MoResponseMsg prototype) {
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
     * Protobuf type {@code cn.weit.happymo.message.MoResponseMsg}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:cn.weit.happymo.message.MoResponseMsg)
        cn.weit.happymo.message.MoResponse.MoResponseMsgOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return cn.weit.happymo.message.MoResponse.internal_static_cn_weit_happymo_message_MoResponseMsg_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return cn.weit.happymo.message.MoResponse.internal_static_cn_weit_happymo_message_MoResponseMsg_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                cn.weit.happymo.message.MoResponse.MoResponseMsg.class, cn.weit.happymo.message.MoResponse.MoResponseMsg.Builder.class);
      }

      // Construct using cn.weit.happymo.message.MoResponse.MoResponseMsg.newBuilder()
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
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        msgType_ = 0;

        version_ = 0;

        state_ = 0;

        cause_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return cn.weit.happymo.message.MoResponse.internal_static_cn_weit_happymo_message_MoResponseMsg_descriptor;
      }

      @java.lang.Override
      public cn.weit.happymo.message.MoResponse.MoResponseMsg getDefaultInstanceForType() {
        return cn.weit.happymo.message.MoResponse.MoResponseMsg.getDefaultInstance();
      }

      @java.lang.Override
      public cn.weit.happymo.message.MoResponse.MoResponseMsg build() {
        cn.weit.happymo.message.MoResponse.MoResponseMsg result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public cn.weit.happymo.message.MoResponse.MoResponseMsg buildPartial() {
        cn.weit.happymo.message.MoResponse.MoResponseMsg result = new cn.weit.happymo.message.MoResponse.MoResponseMsg(this);
        result.msgType_ = msgType_;
        result.version_ = version_;
        result.state_ = state_;
        result.cause_ = cause_;
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
        if (other instanceof cn.weit.happymo.message.MoResponse.MoResponseMsg) {
          return mergeFrom((cn.weit.happymo.message.MoResponse.MoResponseMsg)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(cn.weit.happymo.message.MoResponse.MoResponseMsg other) {
        if (other == cn.weit.happymo.message.MoResponse.MoResponseMsg.getDefaultInstance()) return this;
        if (other.msgType_ != 0) {
          setMsgTypeValue(other.getMsgTypeValue());
        }
        if (other.getVersion() != 0) {
          setVersion(other.getVersion());
        }
        if (other.state_ != 0) {
          setStateValue(other.getStateValue());
        }
        if (!other.getCause().isEmpty()) {
          cause_ = other.cause_;
          onChanged();
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
        cn.weit.happymo.message.MoResponse.MoResponseMsg parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (cn.weit.happymo.message.MoResponse.MoResponseMsg) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int msgType_ = 0;
      /**
       * <code>.cn.weit.happymo.message.MsgType msgType = 1;</code>
       */
      public int getMsgTypeValue() {
        return msgType_;
      }
      /**
       * <code>.cn.weit.happymo.message.MsgType msgType = 1;</code>
       */
      public Builder setMsgTypeValue(int value) {
        msgType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>.cn.weit.happymo.message.MsgType msgType = 1;</code>
       */
      public cn.weit.happymo.message.MsgTypeEnum.MsgType getMsgType() {
        @SuppressWarnings("deprecation")
        cn.weit.happymo.message.MsgTypeEnum.MsgType result = cn.weit.happymo.message.MsgTypeEnum.MsgType.valueOf(msgType_);
        return result == null ? cn.weit.happymo.message.MsgTypeEnum.MsgType.UNRECOGNIZED : result;
      }
      /**
       * <code>.cn.weit.happymo.message.MsgType msgType = 1;</code>
       */
      public Builder setMsgType(cn.weit.happymo.message.MsgTypeEnum.MsgType value) {
        if (value == null) {
          throw new NullPointerException();
        }
        
        msgType_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>.cn.weit.happymo.message.MsgType msgType = 1;</code>
       */
      public Builder clearMsgType() {
        
        msgType_ = 0;
        onChanged();
        return this;
      }

      private int version_ ;
      /**
       * <code>int32 version = 2;</code>
       */
      public int getVersion() {
        return version_;
      }
      /**
       * <code>int32 version = 2;</code>
       */
      public Builder setVersion(int value) {
        
        version_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 version = 2;</code>
       */
      public Builder clearVersion() {
        
        version_ = 0;
        onChanged();
        return this;
      }

      private int state_ = 0;
      /**
       * <code>.cn.weit.happymo.message.State state = 3;</code>
       */
      public int getStateValue() {
        return state_;
      }
      /**
       * <code>.cn.weit.happymo.message.State state = 3;</code>
       */
      public Builder setStateValue(int value) {
        state_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>.cn.weit.happymo.message.State state = 3;</code>
       */
      public cn.weit.happymo.message.ServerState.State getState() {
        @SuppressWarnings("deprecation")
        cn.weit.happymo.message.ServerState.State result = cn.weit.happymo.message.ServerState.State.valueOf(state_);
        return result == null ? cn.weit.happymo.message.ServerState.State.UNRECOGNIZED : result;
      }
      /**
       * <code>.cn.weit.happymo.message.State state = 3;</code>
       */
      public Builder setState(cn.weit.happymo.message.ServerState.State value) {
        if (value == null) {
          throw new NullPointerException();
        }
        
        state_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>.cn.weit.happymo.message.State state = 3;</code>
       */
      public Builder clearState() {
        
        state_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object cause_ = "";
      /**
       * <code>string cause = 4;</code>
       */
      public java.lang.String getCause() {
        java.lang.Object ref = cause_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          cause_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string cause = 4;</code>
       */
      public com.google.protobuf.ByteString
          getCauseBytes() {
        java.lang.Object ref = cause_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          cause_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string cause = 4;</code>
       */
      public Builder setCause(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        cause_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string cause = 4;</code>
       */
      public Builder clearCause() {
        
        cause_ = getDefaultInstance().getCause();
        onChanged();
        return this;
      }
      /**
       * <code>string cause = 4;</code>
       */
      public Builder setCauseBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        cause_ = value;
        onChanged();
        return this;
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


      // @@protoc_insertion_point(builder_scope:cn.weit.happymo.message.MoResponseMsg)
    }

    // @@protoc_insertion_point(class_scope:cn.weit.happymo.message.MoResponseMsg)
    private static final cn.weit.happymo.message.MoResponse.MoResponseMsg DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new cn.weit.happymo.message.MoResponse.MoResponseMsg();
    }

    public static cn.weit.happymo.message.MoResponse.MoResponseMsg getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<MoResponseMsg>
        PARSER = new com.google.protobuf.AbstractParser<MoResponseMsg>() {
      @java.lang.Override
      public MoResponseMsg parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new MoResponseMsg(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<MoResponseMsg> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<MoResponseMsg> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public cn.weit.happymo.message.MoResponse.MoResponseMsg getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_cn_weit_happymo_message_MoResponseMsg_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_cn_weit_happymo_message_MoResponseMsg_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020MoResponse.proto\022\027cn.weit.happymo.mess" +
      "age\032\rMsgType.proto\032\013State.proto\"\221\001\n\rMoRe" +
      "sponseMsg\0221\n\007msgType\030\001 \001(\0162 .cn.weit.hap" +
      "pymo.message.MsgType\022\017\n\007version\030\002 \001(\005\022-\n" +
      "\005state\030\003 \001(\0162\036.cn.weit.happymo.message.S" +
      "tate\022\r\n\005cause\030\004 \001(\tB\014B\nMoResponseP\000P\001b\006p" +
      "roto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          cn.weit.happymo.message.MsgTypeEnum.getDescriptor(),
          cn.weit.happymo.message.ServerState.getDescriptor(),
        }, assigner);
    internal_static_cn_weit_happymo_message_MoResponseMsg_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_cn_weit_happymo_message_MoResponseMsg_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_cn_weit_happymo_message_MoResponseMsg_descriptor,
        new java.lang.String[] { "MsgType", "Version", "State", "Cause", });
    cn.weit.happymo.message.MsgTypeEnum.getDescriptor();
    cn.weit.happymo.message.ServerState.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
