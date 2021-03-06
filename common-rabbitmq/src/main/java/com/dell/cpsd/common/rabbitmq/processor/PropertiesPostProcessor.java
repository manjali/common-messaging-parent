/**
 * Copyright &copy; 2017 Dell Inc. or its subsidiaries.  All Rights Reserved.
 * Dell EMC Confidential/Proprietary Information
 */

package com.dell.cpsd.common.rabbitmq.processor;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;

/**
 * The default message post processor to handle message properties.
 * <p>
 * <p>
 * Copyright &copy; 2017 Dell Inc. or its subsidiaries.  All Rights Reserved.
 * Dell EMC Confidential/Proprietary Information
 * </p>
 * @version 1.0
 * @since SINCE-TDB
 */
public class PropertiesPostProcessor<M extends MessageProperties> implements MessagePostProcessor
{
    /**
     * The properties to use in post processing of the message.
     */
    private M properties = null;

    /**
     * PropertiesPostProcessor constructor
     *
     * @param properties The properties to use in post processing.
     * @since 1.0
     */
    public PropertiesPostProcessor(M properties)
    {
        super();

        if (properties == null)
        {
            throw new IllegalArgumentException("The message properties container is null.");
        }

        this.properties = properties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Message postProcessMessage(final Message message) throws AmqpException
    {
        if (message == null)
        {
            return message;
        }

        final MessageProperties messageProperties = message.getMessageProperties();

        messageProperties.setTimestamp(this.properties.getTimestamp());

        final byte[] correlationId = this.properties.getCorrelationId();
        messageProperties.setCorrelationId(correlationId);

        messageProperties.setCorrelationIdString(this.properties.getCorrelationIdString());

        messageProperties.setReplyTo(this.properties.getReplyTo());
        messageProperties.setExpiration(this.properties.getExpiration());

        return message;
    }
}
