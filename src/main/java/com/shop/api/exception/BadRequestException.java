package com.shop.api.exception;

import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * The Class BadRequestException.
 *
 * @author  Martin Slavov
 * @version 1.0
 * @since   2018-08-01 
 */
public class BadRequestException extends Exception {
 
	/** The Constant serialVersionUID. */
	static final long serialVersionUID = 1L;
	
    /**
     * Instantiates a new bad request exception.
     *
     * @param clazz the clazz
     * @param searchParamsMap the search params map
     */
    public BadRequestException(Class clazz, String... searchParamsMap) {
        super(BadRequestException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }


	/**
	 * Generate message.
	 *
	 * @param entity the entity
	 * @param searchParams the search params
	 * @return the string
	 */
	private static String generateMessage(String entity, Map<String, String> searchParams) {
        return "Bad request for " +
        		StringUtils.capitalize(entity) + 
                searchParams;
    }

    /**
     * To map.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param keyType the key type
     * @param valueType the value type
     * @param entries the entries
     * @return the map
     */
    private static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }

}
